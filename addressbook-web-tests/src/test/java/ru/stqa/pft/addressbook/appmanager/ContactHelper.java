package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

   public ContactHelper(WebDriver wd) {
      super(wd);
   }

   public void initContactCreation() {
      click(By.linkText("add new"));
   }

   public void fillContactForm(ContactData contactData, boolean creation) {
      type(By.name("firstname"), contactData.getFirstName());
      type(By.name("lastname"), contactData.getSecondName());
      attach(By.name("photo"), contactData.getPhoto());
      type(By.name("address"), contactData.getAddress());
      type(By.name("email"), contactData.getEmail());
      type(By.name("email2"), contactData.getEmail2());
      type(By.name("email3"), contactData.getEmail3());
      type(By.name("home"), contactData.getHomePhone());
      type(By.name("mobile"), contactData.getMobilePhone());
      type(By.name("work"), contactData.getWorkPhone());

      if (creation) {
         new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      } else {
         Assert.assertFalse(isElementPresent(By.name("new_group")));
      }


   }

   public void selectContactById(int id) {
      wd.findElement(By.cssSelector("input[id ='" + id + "']")).click();
   }

   public void deleteSelectedContact() {
      click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
   }

   public void submitContactCreation() {
      click(By.xpath("//div[@id='content']/form/input[21]"));
   }

   public void acceptContactDeletion() {
      wd.switchTo().alert().accept();
   }

   public void initContactModificationById(int id) {
      wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
   }

   public void submitContactModification() {
      click(By.xpath("//div[@id='content']/form[1]/input[22]"));
   }

   public void returnToHomePage() {
      click(By.linkText("home page"));
   }

   public void create(ContactData contact, boolean creation) {
      initContactCreation();
      fillContactForm(contact, creation);
      submitContactCreation();
      returnToHomePage();
   }

   public void modify(ContactData contact) {
      initContactModificationById(contact.getId());
      fillContactForm(contact, false);
      submitContactModification();
      returnToHomePage();
   }

   public void delete(ContactData contact) {
      selectContactById(contact.getId());
      deleteSelectedContact();
      acceptContactDeletion();
   }

   public boolean isThereAContact() {
      return isElementPresent(By.name("selected[]"));
   }

   public int getContactCount() {
      return wd.findElements(By.name("selected[]")).size();
   }

   public Contacts all() {
      Contacts contacts = new Contacts();
      List<WebElement> elements = wd.findElements(By.cssSelector("[name = entry]"));
      for (WebElement element : elements) {
         int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
         String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
         String name = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
         String address = element.findElement(By.cssSelector("td:nth-child(4)")).getText();
         String allEmails = element.findElement(By.cssSelector("td:nth-child(5)")).getText();
         String allPhones = element.findElement(By.cssSelector("td:nth-child(6)")).getText();

         ContactData contact = new ContactData()
                 .withId(id)
                 .withFirstName(name).withLastName(lastName)
                 .withAddress(address)
                 .withAllEmails(allEmails)
                 .withAllPhones(allPhones)
                 .withGroup(null);
         contacts.add(contact);
      }
      return contacts;
   }

   public ContactData infoFromEditForm(ContactData contact) {
      initContactModificationById(contact.getId());
      String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
      String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
      String address = wd.findElement(By.name("address")).getAttribute("value");
      String home = wd.findElement(By.name("home")).getAttribute("value");
      String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
      String work = wd.findElement(By.name("work")).getAttribute("value");
      String email = wd.findElement(By.name("email")).getAttribute("value");
      String email2 = wd.findElement(By.name("email2")).getAttribute("value");
      String email3 = wd.findElement(By.name("email3")).getAttribute("value");
      wd.navigate().back();
      return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
              .withAddress(address).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
              .withEmail(email).withEmail2(email2).withEmail3(email3);
   }
}