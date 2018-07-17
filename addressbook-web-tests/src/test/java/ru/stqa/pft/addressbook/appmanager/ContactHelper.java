package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
      type(By.name("address"), contactData.getAddress());
      type(By.name("email"), contactData.getEmail());
      type(By.name("home"), contactData.getPhone1());
      type(By.name("mobile"), contactData.getPhone2());

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

   public Set<ContactData> all() {
      Set<ContactData> contacts = new HashSet<ContactData>();
      List<WebElement> elements = wd.findElements(By.cssSelector("[name = entry]"));
      for (WebElement element : elements) {
         int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
         String name = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
         String secondName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
         ContactData contact = new ContactData()
                 .withId(id)
                 .withFirstName(name).withSecondName(secondName)
                 .withAddress(null)
                 .withEmail(null)
                 .withPhone1(null).withPhone2(null)
                 .withGroup(null);
         contacts.add(contact);
      }
      return contacts;
   }
}