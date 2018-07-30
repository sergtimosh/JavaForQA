package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      Groups groups = app.db().groups();
      app.goTo().HomePage();
      if (groups.size() == 0) {
         app.goTo().groupPage();
         GroupData newGroup = new GroupData().withName("test 1").withHeader("test 2").withFooter("test 3");
         app.group().create(newGroup);
      }
   }

   @DataProvider
   public Iterator<Object[]> validContactsFromJson() throws IOException {
      try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
         String json = "";
         String line = reader.readLine();
         while (line != null) {
            json += line;
            line = reader.readLine();
         }
         Gson gson = new Gson();
         List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
         }.getType());
         return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
      }
   }

   @Test(dataProvider = "validContactsFromJson")
   public void testContactCreation(ContactData contact) {
      Groups groups = app.db().groups();
      app.goTo().HomePage();
      Contacts before = app.db().contacts();
      app.contact().create(contact.inGroup(groups.iterator().next()), true);
      Contacts after = app.db().contacts();

      assertThat(after.size(), equalTo(before.size() + 1));
      assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
   }

}
