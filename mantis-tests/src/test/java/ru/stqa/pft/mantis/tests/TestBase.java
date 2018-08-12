package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertTrue;

public class TestBase {


   protected static final ApplicationManager app
           = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

   @BeforeSuite
   public void setUp() throws Exception {
      app.init();
      app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
   }

   @AfterSuite
   public void tearDown() throws IOException {
      app.ftp().restore("config_inc.php.bak", "config_inc.php");
      app.stop();
   }


   public void ensureMantisUserExist() throws MessagingException, IOException {
      Long now = System.currentTimeMillis();
      UserData newUser = new UserData()
              .withUsername(String.format("user%s", now))
              .withEmail(String.format("user%s@localhost", now))
              .withPasswordMail("password").withPasswordMantis("password1");
      if (app.db().getUserData().size() == 0) {
         ensureMailUserExist(newUser);
         app.registration().start(newUser);
         List<MailMessage> mailMessages = app.james().waitForMail(newUser, 60000);
         String confirmationLink = findConfirmationLink(mailMessages, newUser);
         app.registration().finish(confirmationLink, newUser);
         assertTrue(app.newSession().login(newUser));
      }
   }

   public void ensureMailUserExist(UserData user) {
      if (app.james().doesUserExist(user.getUsername()) == false) {
         app.james().createUser(user);
      }
   }


   public String findConfirmationLink(List<MailMessage> mailMessages, UserData userData) {
      MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(userData.getEmail())).findFirst().get();
      VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
      return regex.getText(mailMessage.text);
   }

   public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
      String adminLogin = app.getProperty("web.adminlogin");
      String adminPassword = app.getProperty("web.adminPassword");
      MantisConnectPortType mc = app.soap().getMantisConnect();
      String status = mc.mc_issue_get(adminLogin, adminPassword, BigInteger.valueOf(issueId)).getStatus().getName();
      if (status.equals("closed")) {
         return false;
      }
      return true;
   }

   public boolean isIssueOpenRest(int issueId) throws IOException {
      String json = app.rest().getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues.json?limit=1000"))
              .returnContent().asString();
      JsonElement parsed = new JsonParser().parse(json);
      JsonElement issuesJson = parsed.getAsJsonObject().get("issues");
      Set<Issue> issues = new Gson().fromJson(issuesJson, new TypeToken<Set<Issue>>() {}.getType());
      for (Issue issue : issues) {
         if (issue.getId() == issueId && issue.getState_name().equals("Closed")) {
            return false;
         }
      }
      return true;
   }

   public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
      if (isIssueOpen(issueId)) {
         throw new SkipException("Ignored because of issue " + issueId);
      }
   }

   public void skipIfNotFixedRest(int issueId) throws IOException {
      if (isIssueOpenRest(issueId)) {
         throw new SkipException("Ignored because of issue " + issueId);
      }
   }

}
