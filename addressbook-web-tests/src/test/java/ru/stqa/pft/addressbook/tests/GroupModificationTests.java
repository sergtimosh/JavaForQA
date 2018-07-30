package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      ensureGroupPresent();
   }

   @Test
   public void testGroupModification() {
      Groups before = app.db().groups();
      GroupData modifiedGroup = before.iterator().next();
      GroupData group = new GroupData()
              .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
      app.group().modify(group);
      assertThat(app.group().count(), equalTo(before.size())); //assertion is needed, to have some control over ui side despite it is slows down test execution
      Groups after = app.db().groups();
      assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
      verifyGroupListInUi();
   }

}
