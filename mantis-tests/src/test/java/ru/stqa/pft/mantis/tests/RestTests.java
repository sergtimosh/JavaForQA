package ru.stqa.pft.mantis.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

//   @BeforeMethod
   public void checkIfBugFixed() throws IOException {
      int issueId = 144;
      skipIfNotFixedRest(issueId);
   }

//   @Test
   public void testCreateIssue() throws IOException {
      Set<Issue> oldIssues = app.rest().getIssues();
      Issue newIssue = new Issue()
              .withSubject("Test issue")
              .withDescription("New test issue");
      int issueId = createIssue(newIssue);
      Set<Issue> newIssues = app.rest().getIssues();
      oldIssues.add(newIssue.withId(issueId));
      assertEquals(newIssues, oldIssues);
   }

   private int createIssue(Issue newIssue) throws IOException {
      String json = app.rest().getExecutor().execute(Request.Post("http://bugify.stqa.ru/api/issues.json")
              .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject().toString()), new BasicNameValuePair("description", newIssue.getDescription().toString()), new BasicNameValuePair("state_name", newIssue.getState_name().toString())))
              .returnContent().asString();
      JsonElement parsed = new JsonParser().parse(json);
      return parsed.getAsJsonObject().get("issue_id").getAsInt();
   }

   @Test
   public void testCloseIssue() throws IOException {
      Set<Issue> oldIssues = app.rest().getIssues();
      Issue selectedIssue = oldIssues.iterator().next();
      oldIssues.remove(selectedIssue);
      oldIssues.add((Issue) closeIssue(selectedIssue));
      Set<Issue> newIssues = app.rest().getIssues();
      assertEquals(newIssues, oldIssues);
   }

   private Set<Issue> closeIssue(Issue issue) throws IOException {
      String json = app.rest().getExecutor().execute(Request.Post("http://bugify.stqa.ru/api/issues/" + issue.getId() + ".json")
              .bodyForm(new BasicNameValuePair("method", "update"), new BasicNameValuePair("subject", issue.getSubject()), new BasicNameValuePair("description", issue.getDescription()), new BasicNameValuePair("state_name", "Closed")))
              .returnContent().asString();
      JsonElement parsed = new JsonParser().parse(json);
      JsonElement issuesJson = parsed.getAsJsonObject().get("issues");
      return new Gson().fromJson(issuesJson, new TypeToken<Set<Issue>>() {}.getType());
   }

}

