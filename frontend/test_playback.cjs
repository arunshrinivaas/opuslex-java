const { chromium, webkit } = require('playwright');

async function testBrowser(browserType, name) {
    console.log(`\n=== Testing ${name} ===`);
    const browser = await browserType.launch();
    const page = await browser.newPage();
    
    // We will monitor console for errors
    page.on('console', msg => {
        if(msg.type() === 'warning' || msg.type() === 'error') {
            console.log(`[${name} Console] ${msg.type()}: ${msg.text()}`);
        }
    });

    await page.goto('http://localhost:5174');
    await page.waitForTimeout(1000);
    
    let videoState = await page.evaluate(() => {
        const v = document.querySelector('video');
        return {
            src: v.currentSrc,
            paused: v.paused,
            readyState: v.readyState,
            currentTime: v.currentTime,
            muted: v.muted,
            playsInline: v.playsInline
        };
    });
    console.log(`${name} Initial Video State:`, videoState);

    // Test transition from law1 -> law2
    await page.evaluate(() => {
        const v = document.querySelector('video');
        v.currentTime = v.duration - 0.5;
    });
    await page.waitForTimeout(2000);
    
    videoState = await page.evaluate(() => {
        const v = document.querySelector('video');
        return {
            src: v.currentSrc,
            paused: v.paused,
            currentTime: v.currentTime
        };
    });
    console.log(`${name} Video State after transition (expected law2):`, videoState);

    // Test transition from law2 -> law3
    await page.evaluate(() => {
        const v = document.querySelector('video');
        v.currentTime = v.duration - 0.5;
    });
    await page.waitForTimeout(2000);

    videoState = await page.evaluate(() => {
        const v = document.querySelector('video');
        return {
            src: v.currentSrc,
            paused: v.paused,
            currentTime: v.currentTime
        };
    });
    console.log(`${name} Video State after transition 2 (expected law3):`, videoState);

    // Test Login
    await page.fill('input[type="email"]', 'admin@example.com');
    await page.fill('input[type="password"]', 'admin');
    await page.click('button:has-text("Log in")');
    await page.waitForTimeout(2000);
    
    const url = page.url();
    console.log(`${name} Final URL (expected /workspace):`, url);

    await browser.close();
}

(async () => {
    try {
        await testBrowser(chromium, 'Chrome/Chromium');
        await testBrowser(webkit, 'Safari/WebKit');
    } catch (e) {
        console.error(e);
    }
})();
