import { NgScsAppPage } from './app.po';

describe('ng-scs-app App', function() {
  let page: NgScsAppPage;

  beforeEach(() => {
    page = new NgScsAppPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
