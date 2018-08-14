package ru.stqa.pft.rest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

   @BeforeMethod
   public void checkIfBugFixed() throws IOException {
      int issueId = 52;
      skipIfNotFixed(issueId);
   }

   @Test
   public void testCreateIssue() throws IOException {
      Set<Issue> oldIssues = app.rest().getIssues();
      Issue newIssue = new Issue()
              .withSubject("Test issue")
              .withDescription("New test issue");
      int issueId = app.rest().createIssue(newIssue);
      Set<Issue> newIssues = app.rest().getIssues();
      oldIssues.add(newIssue.withId(issueId));
      assertEquals(newIssues, oldIssues);
   }

   @Test
   public void testCloseIssue() throws IOException {
      Set<Issue> oldIssues = app.rest().getIssues();
      Issue selectedIssue = oldIssues.iterator().next();
      oldIssues.remove(selectedIssue);
      app.rest().closeIssue(selectedIssue);
      Set<Issue> newIssues = app.rest().getIssues();
      assertEquals(newIssues.size(), oldIssues.size());
      assertEquals(newIssues, oldIssues);
      System.out.println("Bug with id "+ selectedIssue.getId() + " was succesfully closed");
   }

}

