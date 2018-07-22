package ru.stqa.pft.addressbook.model;

import java.io.File;
import java.util.Objects;

public class ContactData {

   private int id = Integer.MAX_VALUE;
   private String firstName;
   private String lastName;
   private String address;
   private String homePhone;
   private String mobilePhone;
   private String workPhone;
   private String allPhones;
   private String email;
   private String email2;
   private String email3;
   private String allEmails;
   private String group;
   private File photo;


   public ContactData() {
   }

   public int getId() {
      return id;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getSecondName() {
      return lastName;
   }

   public String getAddress() {
      return address;
   }

   public String getEmail() {
      return email;
   }

   public String getEmail2() {
      return email2;
   }

   public String getEmail3() {
      return email3;
   }

   public String getAllEmails() {
      return allEmails;
   }

   public String getHomePhone() {
      return homePhone;
   }

   public String getMobilePhone() {
      return mobilePhone;
   }

   public String getWorkPhone() {
      return workPhone;
   }

   public String getAllPhones() {
      return allPhones;
   }

   public String getGroup() {
      return group;
   }

   public File getPhoto() {
      return photo;
   }


   public ContactData withId(int id) {
      this.id = id;
      return this;
   }

   public ContactData withFirstName(String firstName) {
      this.firstName = firstName;
      return this;
   }

   public ContactData withLastName(String secondName) {
      this.lastName = secondName;
      return this;
   }

   public ContactData withAddress(String address) {
      this.address = address;
      return this;
   }

   public ContactData withEmail(String email) {
      this.email = email;
      return this;
   }

   public ContactData withEmail2(String email2) {
      this.email2 = email2;
      return this;
   }

   public ContactData withEmail3(String email3) {
      this.email3 = email3;
      return this;
   }

   public ContactData withAllEmails(String allEmails) {
      this.allEmails = allEmails;
      return this;
   }

   public ContactData withHomePhone(String phone1) {
      this.homePhone = phone1;
      return this;
   }

   public ContactData withMobilePhone(String phone2) {
      this.mobilePhone = phone2;
      return this;
   }

   public ContactData withWorkPhone(String phone3) {
      this.workPhone = phone3;
      return this;
   }

   public ContactData withAllPhones(String allPhones) {
      this.allPhones = allPhones;
      return this;
   }

   public ContactData withGroup(String group) {
      this.group = group;
      return this;
   }

   public ContactData withPhoto(File photo) {
      this.photo = photo;
      return this;
   }

   @Override
   public String toString() {
      return "ContactData{" +
              "id='" + id + '\'' +
              ", firstName='" + firstName + '\'' +
              ", secondName='" + lastName + '\'' +
              '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ContactData that = (ContactData) o;
      return id == that.id &&
              Objects.equals(firstName, that.firstName) &&
              Objects.equals(lastName, that.lastName);
   }

   @Override
   public int hashCode() {

      return Objects.hash(id, firstName, lastName);
   }
}
