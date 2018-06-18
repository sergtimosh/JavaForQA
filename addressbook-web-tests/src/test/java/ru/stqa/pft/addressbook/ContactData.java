package ru.stqa.pft.addressbook;

public class ContactData {
  private final String firstname;
  private final String secondname;
  private final String address;
  private final String email;
  private final String phone1;
  private final String phone2;

  public ContactData(String firstname, String secondname, String address, String email, String phone1, String phone2) {
    this.firstname = firstname;
    this.secondname = secondname;
    this.address = address;
    this.email = email;
    this.phone1 = phone1;
    this.phone2 = phone2;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getSecondname() {
    return secondname;
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
}
