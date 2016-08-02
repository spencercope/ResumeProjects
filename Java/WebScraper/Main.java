package emomed;

public class Main 
{
    public static void main(String[] args) throws InterruptedException 
    {
        WebScraper ws = new WebScraper();
        ws.openSite();
        ws.login();
        ws.clickElemPartEl();
        ws.enterDate();
        ws.loopAndGrab();
        ws.closeBrowser();
    }
}
