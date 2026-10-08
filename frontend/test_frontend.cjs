const { chromium } = require('playwright');

(async () => {
  const browser = await chromium.launch();
  const page = await browser.newPage();
  
  page.on('console', msg => console.log('PAGE LOG:', msg.text()));
  
  await page.goto('http://localhost:5173');
  
  await page.click('text="Sign in"');
  await page.waitForTimeout(2000);
  
  await page.route('http://127.0.0.1:8000/api/v1/auth/google', route => {
    console.log('Intercepted Google auth call!');
    route.fulfill({
      status: 200,
      contentType: 'application/json',
      body: JSON.stringify({
        mfa_required: false,
        access_token: 'fake_token',
        token_type: 'bearer',
        user: { id: 1, email: 'test@example.com', full_name: 'Test', role: 'viewer', mfa_enabled: false }
      })
    });
  });

  console.log('Calling window.__handleGoogleCredential...');
  await page.evaluate(() => {
    if (window.__handleGoogleCredential) {
      window.__handleGoogleCredential({ credential: "fake" });
    } else {
      console.log('window.__handleGoogleCredential NOT FOUND!');
    }
  });

  await page.waitForTimeout(3000);
  
  const content = await page.content();
  if (content.includes('Test')) {
      console.log('Dashboard mounted successfully!');
  } else if (content.includes('Create account')) {
      console.log('Returned to initial OpusLex login page!');
  } else {
      console.log('Unknown state. Content excerpt:', content.substring(0, 500));
  }
  
  await browser.close();
})();
