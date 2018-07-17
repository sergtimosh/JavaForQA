package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

   private int id = Integer.MAX_VALUE;
   private String firstName;
   private String secondName;

   private String address;

   private String email;
   private String phone1;
   private String phone2;
   private String group;

   public int getId() {
      return id;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getSecondName() {
      return secondName;
   }

   public String getAddress() {
      return address;
   }

   public String getEmail() {
      return email;
   }

   public String getPhone1() {
      return phone1;
   }

   public String getPhone2() {
      return phone2;
   }

   public String getGroup() {
      return group;
   }

   public ContactData withId(int id) {
      this.id = id;
      return this;
   }

   public ContactData withFirstName(String firstName) {
      this.firstName = firstName;
      return this;
   }

   public ContactData withSecondName(String secondName) {
      this.secondName = secondName;
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

   public ContactData withPhone1(String phone1) {
      this.phone1 = phone1;
      return this;
   }

   public ContactData withPhone2(String phone2) {
      this.phone2 = phone2;
      return this;
   }

   public ContactData withGroup(String group) {
      this.group = group;
      return this;
   }

   @Override
   public String toString() {
      return "ContactData{" +
              "id='" + id + '\'' +
              ", firstName='" + firstName + '\'' +
              ", secondName='" + secondName + '\'' +
              '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ContactData that = (ContactData) o;
      return id == that.id &&
              Objects.equals(firstName, that.firstName) &&
              Objects.equals(secondName, that.secondName);
   }

   @Override
   public int hashCode() {

      return Objects.hash(id, firstName, secondName);
   }
}
