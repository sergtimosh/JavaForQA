package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

   @Test
   public void testContactCreation() {
      app.getNavigationHelper().goToHomePage();
      List<ContactData> before = app.getContactHelper().getContactList();
      ContactData group = new ContactData("Arsen", "Tymoshenko", "17. Sichovy str.\n116 apt", "serdtimosh@gmail.com", "+0472313950", "+380945643839", "test1");
      app.getContactHelper().createContact(group, true);
      List<ContactData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(), before.size() + 1);


      group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
      before.add(group);
      Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
   }

}
