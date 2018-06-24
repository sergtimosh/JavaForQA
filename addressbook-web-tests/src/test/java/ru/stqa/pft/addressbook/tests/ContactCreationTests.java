package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().createContact(new ContactData("Serhiy", "Tymoshenko", "17. Sichovy str.\n116 apt", "serdtimosh@gmail.com", "+0472313950", "+380945643839", "test1"), true);
  }

}
