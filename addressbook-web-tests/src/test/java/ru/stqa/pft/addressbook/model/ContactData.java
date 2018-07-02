package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

   private int id;
   private final String firstName;
   private final String secondName;
   private final String address;
   private final String email;
   private final String phone1;
   private final String phone2;
   private String group;

   public ContactData(String firstName, String secondName, String address, String email, String phone1, String phone2, String group) {
      this.id = Integer.MAX_VALUE;
      this.firstName = firstName;
      this.secondName = secondName;
      this.address = address;
      this.email = email;
      this.phone1 = phone1;
      this.phone2 = phone2;
      this.group = group;
   }
   public ContactData(int id, String firstName, String secondName, String address, String email, String phone1, String phone2, String group) {
      this.id = id;
      this.firstName = firstName;
      this.secondName = secondName;
      this.address = address;
      this.email = email;
      this.phone1 = phone1;
      this.phone2 = phone2;
      this.group = group;
   }

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

   public void setId(int id) {
      this.id = id;
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
      return Objects.equals(firstName, that.firstName) &&
              Objects.equals(secondName, that.secondName);
   }

   @Override
   public int hashCode() {

      return Objects.hash(firstName, secondName);
   }
}
