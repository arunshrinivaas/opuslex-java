const { chromium } = require('/Users/arun/.gemini/antigravity-ide/brain/3d78f66b-f2e1-4b88-b3f3-252971a71eb7/scratch/node_modules/playwright');
(async () => {
  const browser = await chromium.launch();
  const page = await browser.newPage();
  page.on('console', msg => console.log('BROWSER CONSOLE:', msg.text()));
  page.on('pageerror', err => console.log('PAGE ERROR:', err.message));
  page.on('requestfailed', req => console.log('REQUEST FAILED:', req.url(), req.failure().errorText));
  page.on('response', res => {
    if (res.status() >= 400 && res.url().includes('google')) {
      console.log('HTTP ERROR', res.status(), res.url());
    }
  });

  await page.goto('http://localhost:5173');
  await page.waitForTimeout(2000);
  
  await page.click('button:has-text("Sign in with Google")');
  await page.waitForTimeout(3000);
  await browser.close();
})();
