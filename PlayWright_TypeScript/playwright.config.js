// @ts-check
const { devices } = require('@playwright/test');
console.log("Executing playwright.config.js");
const config = {
  testDir: './tests',
  retries: 0,
  workers: 2,
  
  /* Run your local dev server before starting tests */
  webServer: undefined,

  
  
  /* Maximum time one test can run for. */
  timeout: 20 * 1000,
  expect: {
    timeout: 10 * 1000
  },
  
  reporter: 'html',
  /* Shared settings for all the projects below. See https://playwright.dev/docs/api/class-testoptions. */
  use: {
    browserName: 'chromium',
    headless: false,
    screenshot: 'only-on-failure',
    trace: 'on', //off,on
  },
};

module.exports = config;
