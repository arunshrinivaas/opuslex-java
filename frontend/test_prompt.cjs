const { chromium } = require('/Users/arun/.gemini/antigravity-ide/brain/3d78f66b-f2e1-4b88-b3f3-252971a71eb7/scratch/node_modules/playwright');
(async () => {
  const browser = await chromium.launch();
  const page = await browser.newPage();
  
  // Intercept console to see the notification
  page.on('console', msg => console.log('BROWSER CONSOLE:', msg.text()));

  // Inject a script to override window.google.accounts.id.prompt to capture the notification
  await page.addInitScript(() => {
    let originalPrompt;
    Object.defineProperty(window, 'google', {
      configurable: true,
      set: function(val) {
        if (val && val.accounts && val.accounts.id) {
          originalPrompt = val.accounts.id.prompt;
          val.accounts.id.prompt = function(callback) {
            console.log('Intercepted prompt() call');
            return originalPrompt.call(this, (notification) => {
              if (notification.isNotDisplayed()) {
                console.log('isNotDisplayed reason:', notification.getNotDisplayedReason());
              }
              if (notification.isSkippedMoment()) {
                console.log('isSkippedMoment reason:', notification.getSkippedReason());
              }
              if (callback) callback(notification);
            });
          };
        }
        this._google = val;
      },
      get: function() { return this._google; }
    });
  });

  await page.goto('http://localhost:5173');
  await page.waitForTimeout(2000);
  
  // Click "Sign in" first to reach the providers stage
  await page.click('button:has-text("Sign in")');
  await page.waitForTimeout(500);

  // Click the Google button
  await page.click('button:has-text("Sign in with Google")');
  await page.waitForTimeout(3000);
  await browser.close();
})();
