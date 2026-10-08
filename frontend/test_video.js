const { chromium } = require('playwright');
(async () => {
  const browser = await chromium.launch();
  const page = await browser.newPage();
  await page.goto('http://localhost:5174');
  
  // Wait a moment for video to start
  await page.waitForTimeout(2000);
  
  const videoState1 = await page.evaluate(() => {
    const v = document.querySelector('video');
    return { src: v.currentSrc, paused: v.paused, readyState: v.readyState, currentTime: v.currentTime };
  });
  console.log('Video 1 State:', videoState1);
  
  // Set time near end
  await page.evaluate(() => { document.querySelector('video').currentTime = document.querySelector('video').duration - 1; });
  await page.waitForTimeout(2000);
  
  const videoState2 = await page.evaluate(() => {
    const v = document.querySelector('video');
    return { src: v.currentSrc, paused: v.paused, readyState: v.readyState, currentTime: v.currentTime };
  });
  console.log('Video 2 State:', videoState2);
  
  await browser.close();
})();
