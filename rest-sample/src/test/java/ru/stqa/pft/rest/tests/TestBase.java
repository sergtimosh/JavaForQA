package ru.stqa.pft.rest.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import ru.stqa.pft.rest.appmanager.ApplicationManager;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;

public class TestBase {


   protected static final ApplicationManager app
           = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));


   private boolean isIssueOpen(int issueId) throws IOException {
      Issue issue = app.rest().getIssue(issueId);
      return !issue.getState_name().equals("Closed");
   }

   public void skipIfNotFixed(int issueId) throws IOException {
      if (isIssueOpen(issueId)) {
         System.out.println("Bug with id "+ issueId + " wasn't yet closed");
         throw new SkipException("Ignored because of issue " + issueId);
      }
   }

}
