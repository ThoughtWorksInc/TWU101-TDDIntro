Test Driven Development in Java

## Introduction

These lessons will teach you the basics of Test Driven Development (TDD) in Java, using JUnit, Mockito, and IntelliJ.

We’re assuming that we don’t need to convince you why you want to do TDD and we’ll only touch lightly on the principles of TDD. Instead we’ll be focusing on the what and how.

## What is TDD?

TDD is the practice of writing a small amount of code (a unit test) that describes the new behavior you wish to add to your program before you implement the behavior itself.

### Unit Tests

You can think of unit tests as tiny programs we write to verify that the methods in our classes do what we expect them to do.

## The TDD Cycle

The following sequence is based on the book *[Test-Driven Development by Exampl*e](http://en.wikipedia.org/wiki/Test-Driven_Development_by_Example).

The basics steps for this process look like this:

1. Write a small failing unit test

2. Make this new test pass in the simplest way possible

3. Clean up any messes we created

## ![image alt text](https://github.com/BillSchofield/TDDIntro/blob/master/src/common/images/TDDCycle.png)

### Add a test

In test-driven development, each new feature begins with writing a test. This test must inevitably fail because it is written before the feature has been implemented. (If it does not fail, then either the proposed "new" feature already exists or the test is defective.) To write a test, the developer must clearly understand the feature's specification and requirements. The developer can accomplish this through [use cases](http://en.wikipedia.org/wiki/Use_case) and [user stories](http://en.wikipedia.org/wiki/User_story) to cover the requirements and exception conditions, and can write the test in whatever testing framework is appropriate to the software environment. This could also be a modification of an existing test. This is a differentiating feature of test-driven development versus writing unit tests *after* the code is written: it makes the developer focus on the requirements *before* writing the code, a subtle but important difference.

### Run all tests and see if the new one fails

This validates that the [test harness](http://en.wikipedia.org/wiki/Test_harness) is working correctly and that the new test does not mistakenly pass without requiring any new code. This step also tests the test itself; it rules out the possibility that the new test always passes, and therefore is worthless. The new test should also fail for the expected reason. This increases confidence (though does not guarantee) that it is testing the right thing, and passes only in intended cases.

### Write some code

The next step is to write code that causes the test to pass. The new code written at this stage is not perfect, and may, for example, pass the test in an inelegant way. That is acceptable because later steps improve and hone it.

At this point, the only purpose of the written code is to pass the test; no further (and therefore untested) functionality should be predicted and 'allowed for' at any stage.  This prevents unnecessary and unspecified code from being written, helping avoid [YAGNI](http://en.wikipedia.org/wiki/You_aren't_gonna_need_it) functionality.

### Run tests

If all test cases now pass, the programmer can be confident that the code meets all the tested requirements. This is a good point from which to begin the final step of the cycle.

### Refactor code

Now the code should be cleaned up. Move code to where it logically belongs. Remove duplication. Make sure variable and method names represent their current use. Clarify constructs that might be misinterpreted. Use Kent Beck's four rules of simple design to guide you, as well as anything else you know about writing clean code. By re-running test cases, you can be confident that [refactoring](http://en.wikipedia.org/wiki/Code_refactoring) is not damaging any existing functionality.

The concept of removing duplication is an important aspect of any software design. In this case it also applies to removing duplication between test code and production code—for example [magic numbers or strings](http://en.wikipedia.org/wiki/Magic_number_(programming)) repeated in both to make the test pass in the "Write some code" step.

### Repeat

Starting with another new test, repeat the cycle to push forward the functionality. The size of the steps should always be small, with as few as 1 to 10 edits between each test run. If new code does not rapidly satisfy a new test, or other tests fail unexpectedly, the programmer should [undo](http://en.wikipedia.org/wiki/Undo) or revert in preference to excessive [debugging](http://en.wikipedia.org/wiki/Debugging). [Continuous integration](http://en.wikipedia.org/wiki/Continuous_integration) helps by providing revertible checkpoints. When using external libraries do not make increments so small that they merely testing the library itself, unless there is some reason to believe that the library is buggy or not sufficiently feature-complete to serve all the needs of the main program being written.

## Unit Tests

As we mentioned above, unit tests are small programs that we use to verify the correctness of our "production" code. The word *unit* refers to a subdivision of the overall program. While others might consider *unit* to mean a class or package, we will only be unit testing at the method level.

Before you start writing a unit test you should know what behavior you want to verify. For instance, you might have a method that returns the plural version of a word. In English, the way we pluralize most words is by adding the letter ‘s’ to the end of the word. That sounds like a great first test case.  

Once you know what behavior you want to verify you can name your test. A great format for test names is, "should<expected behavior>When<situation that behavior depends on>. In our pluralizing example, the expected behavior is ‘add an s’ and the situation is ‘normal word’. That means that we could name out test ‘shouldAddSWhenWordIsNormal’. Since it’s not necessarily clear what it means for a word to be ‘normal’, we could also name the test ‘shouldAddS’ or ‘shouldAddSToWord’.

Once you know the behavior you want to verify and the method where you expect add that behavior, you can start writing your test. We’ll show you how to do this in JUnit.

## JUnit

JUnit is a popular Java unit testing framework. We’re going to use JUnit to create our TDD unit tests.

JUnit example:

<table>
  <tr>
    <td>public class PluralizerTests {
...

@Test
public void shouldAddSWhenWordIsNormal() {
// Arrange our objects
Pluralizer pluralizer = new Pluralizer();

// Action we are testing
String result = pluralizer.pluralize("Cat");

// Assert that the action caused the expected result
assertThat(result, is(“Cats”));
}
...
}</td>
  </tr>
</table>


### Test Classes

We call the class that we are testing the *class under test*. In the example above, the class under test is **`Pluralize**r`.**_ _**All of the unit tests for the *class under test* will live inside a test class named: **`<class under test>Tests.jav**a`.` `

### Anatomy of a Unit Test

There are three sections to every unit test. One set of names for these sections is: Arrange, Action, Assert. Another is: Given, When, Then. 

#### Arrange/Given 

This is where we set the stage for our scenario. That means that we create all of the objects we need for the test in this section. While arranging happens at the top of our test, we often make changes here after working on the other two sections.

#### Action/When

The Action/When section is where we call the method that we are testing (the Action). This should usually be a single method call. 

#### Assert/Then

We verify that the method under test caused the right thing to happen in Assert/Then section of our tests. If you feel like you need more than one assert you should probably split your test.

### Writing a test

If you have a good idea of the behavior you want to test then you should just do this:

1. Create the test method with a name that describe what you are testing

2. Write the test in whatever way makes sense to you

If you don’t have a good sense of what your test to look like when you start writing it, try using this process:

1. Write an empty test with an meaningless name

2. Create an instance of the class you want to test

3. Call the method you want to test

4. Setup the case that will cause the result

5. Assert some result you expect 

6. Name your test

Here’s an example of Test Driving a **`StringJoine**r` class whose job is to *Join* strings.

<table>
  <tr>
    <td>Join
Joining a list of strings means creating a single new string by concatenating the string in list together with a delimiter between them. For instance, joining the strings {"a", “b”, “c”} on the delimiter “,” would result in the string “a,b,c”. Note that there is not a leading or trailing comma.</td>
  </tr>
</table>


1) Create all of the test scaffolding and get it to compile (name your test something ugly).

<table>
  <tr>
    <td>public class StringJoinerTests {
@Test
public void shouldFooWhenBar() {
}
}</td>
  </tr>
</table>


2 & 3) Next, create an instance of the class you are testing and call the method. You should type out the name of the class and method even if they don’t exist yet. In the example below, assume that the class **`StringJoine**r` doesn’t exist yet.

<table>
  <tr>
    <td>public class StringJoinerTests {
@Test
public void shouldFooWhenBar() {
    String result = new StringJoiner().join();
}
}</td>
  </tr>
</table>


Because **`StringJoine**r` doesn’t exist yet, our IDE highlights it in red. In IntelliJ we can click on the class name, press Alt-Enter and choose the option **`Create Class ‘StringJoiner**’`. This will automatically create the class for you. After the class is created our test will look like this...

<table>
  <tr>
    <td>public class StringJoinerTests {
@Test
public void shouldFooWhenBar() {
    String result = new StringJoiner().join();
}
}</td>
  </tr>
</table>


The write method is red because it’s not implemented yet. Hit Alt-Enter and choose **`Create Method ’join**’`. Now we’re calling the method we want to test. Here are some useful questions we can ask:

* What is the smallest piece of new behavior that we can add? 

* What change would it cause? 

These questions should lead us to add an assert that verifies that we got the correct result from joining some strings. Right now the **`joi**n` method returns **`nul**l`. Let’s add the behavior that causes join to return the empty string when the list that is passed in is empty.

Wait a minute, we aren’t passing a list of strings into the **`joi**n` method yet. We also suddenly have enough information to name our test. And we want to add an assert too. When you have more than one thing that could be your next change, write down the options and do them one at at time. Here’s our current ToDo list:

1. Pass list of strings to join

2. Add assert to test

3. Rename this test to ???

4) Let’s pass an empty list into **`joi**n` first.

<table>
  <tr>
    <td>public class StringJoinerTests {
@Test
public void shouldFooWhenBar() {
    List<String> strings = new ArrayList<String>();
    String result = new StringJoiner().join(strings);
}
}</td>
  </tr>
</table>


5) Now we can add our assert that verifies that we got an empty string back.

<table>
  <tr>
    <td>public class StringJoinerTests {
@Test
public void shouldFooWhenBar() {
    List<String> strings = new ArrayList<String>();
    String result = new StringJoiner().join(strings);
    assertThat(result, is(""));
}
}</td>
  </tr>
</table>


6) Now that we really understand what we are testing we can rename our test. Normally you will name your test **before** you start writing it. You should only name it later when you have trouble understanding what you want to test. Let’s rename the test based on what we know about the purpose of our test.

<table>
  <tr>
    <td>public class StringJoinerTests {
@Test
public void shouldJoinIntoAnEmptyStringWhenListIsEmpty() {
    List<String> strings = new ArrayList<String>();
    String result = new StringJoiner().join(strings);
    assertThat(result, is(""));
}
}</td>
  </tr>
</table>


This is a complete unit test. Let’s split it up to clarify what the three sections of the test are.

<table>
  <tr>
    <td>public class StringJoinerTests {
@Test
public void shouldJoinIntoAnEmptyStringWhenListIsEmpty() {
    // Arrange
    StringJoiner joiner = new StringJoiner();
    List<String> strings = new ArrayList<String>();

    // Action
    String result = joiner.join(strings);

    // Assert
    assertThat(result, is(""));
}
}</td>
  </tr>
</table>


## New we can run the test and watch it fail by clicking anywhere in the test file and hitting Ctrl-Shift-F10. 

Now we want to make it pass by writing the simplest code possible. This is how we can make the test pass:

<table>
  <tr>
    <td>public class StringJoiner {
    public String join(List<String> strings) {
        return "";
    }
}</td>
  </tr>
</table>


This is our first passing test! If there was anything for us to refactor we would do so now. Now let’s write more tests. We’ll move a little faster now.

<table>
  <tr>
    <td>    @Test
    public void shouldJoinIntoTheStringWhenListIsOneString(){
        List<String> strings = new ArrayList<String>();
        String aString = "A String";
        strings.add(aString);
        StringJoiner joiner = new StringJoiner();

        String result = joiner.join(strings);

        assertThat(result, is(aString));
    }</td>
  </tr>
</table>


This is a much more interesting test than the first one. There’s a lot going on in the Arrange section, although most of it is just adding a string to the list. Other than the change to how we arrange the objects this test is mostly the same as the first one. The test fails as expect and this is a simple way to make it pass.

<table>
  <tr>
    <td>public class StringJoiner {
    public String join(List<String> strings) {
        if (strings.size() > 0){
            return strings.get(0);
        }
        return "";
    }
}</td>
  </tr>
</table>


Now we want to run both of our tests. A simple way to do this is to click anywhere in the test file that is not inside of a method and hit Ctrl-Shift-F10. Now both tests pass and it’s time to think about refactoring. There’s nothing obvious to refactor in **`StringJoine**r`, but there’s a lot of duplication in our test class. After removing comments and blank lines, it looks like this:

<table>
  <tr>
    <td>public class StringJoinerTests {
    @Test
    public void shouldJoinIntoAnEmptyStringWhenListIsEmpty(){
        List<String> strings = new ArrayList<String>();
        StringJoiner joiner = new StringJoiner();
        String result = joiner.join(strings);
        assertThat(result, is(""));
    }
    @Test
    public void shouldJoinIntoTheStringWhenListIsOneString(){
        List<String> strings = new ArrayList<String>();
        String aString = "A String";
        strings.add(aString);
        StringJoiner joiner = new StringJoiner();
        String result = joiner.join(strings);
        assertThat(result, is(aString));
    }
}</td>
  </tr>
</table>


The blue colored lines are exactly the same in both tests. Let’s fix this while our tests are passing so we can have confidence that we didn’t break anything. We can move change these local variables into instance variables which we initialize in setup method like this:

<table>
  <tr>
    <td>public class StringJoinerTests {
    private List<String> strings;
    private StringJoiner joiner;
    @Before
    public void setUp() throws Exception {
        strings = new ArrayList<String>();
        joiner = new StringJoiner();
    }
    @Test
    public void shouldJoinIntoAnEmptyStringWhenListIsEmpty(){
        assertThat(joiner.join(strings), is(""));
    }
    @Test
    public void shouldJoinIntoTheStringWhenListIsOneString(){
        String aString = "A String";
        strings.add(aString);
        assertThat(joiner.join(strings), is(aString));
    }
}</td>
  </tr>
</table>


Note that we removed the **`resul**t` variable to improve readability.

There’s also a new method call **`setU**p` which has the **`@Befor**e` annotation. Any method that is marked with **`@Befor**e` will be executed before each test in that same class. This allows us to reset the strings and joiner instance variables so that they don’t allow the actions of one test to affect another.

So far we have taken such small steps that out **`StringJoine**r` class doesn’t do much. This is normal for TDD, we’re implementing the behavior we want in very small slices but they will quickly add up to everything we need. Here’s our next test.

<table>
  <tr>
    <td>    @Test
    public void shouldContainBothStringsWhenListIsTwoStrings(){
        strings.add("A");
        strings.add("B");
        assertThat(joiner.join(strings),                     
            both(containsString("A")).
            and(containsString("B")));
    }</td>
  </tr>
</table>


The assert in this test is more complex than in previous tests. **`ContainsStrin**g` verifies that the result of join contains a certain string (in this case "A" or “B”). The **`both/an**d` construct means that both **`containsStrin**g` verifications must be true for the assert to pass. 

When we run all of our tests, we’re happy to see that this new test fails and all of our old tests pass. Now we need to make the new test pass.

<table>
  <tr>
    <td>public class StringJoiner {
    public String join(List<String> strings) {
        String result = "";
        for (String string : strings) {
            result += string;
        }
        return result;
    }
}</td>
  </tr>
</table>


This makes all of our tests pass. Great! So far our **`StringJoine**r` joins all of the strings together, but it doesn’t even know what a delimiter is, much less how to put it between the strings in the list. Our new test should fix that…

<table>
  <tr>
    <td>    @Test
    public void shouldPutDelimiterBetweenStrings(){
        StringJoiner joinerWithDelimiter = new StringJoiner(",");
        strings.add("A");
        strings.add("B");
        assertThat(joinerWithDelimiter.join(strings), is("A,B"));
    }</td>
  </tr>
</table>


Our **`StringJoine**r` now knows about delimiters and it’s constructor takes one as a parameter. Because our constructor changed we have to update all of the places that we create a new **`StringJoine**r`. Fortunately, there is only one other **`new StringJoine**r` in our tests because we removed duplication early on and moved creation of our **`StringJoine**r` into the **`setU**p` method. Now we need to figure out the simplest way to make all of our tests pass.

<table>
  <tr>
    <td>public class StringJoiner {
    private String delimiter;

    public StringJoiner(String delimiter) {
        this.delimiter = delimiter;
    }

    public String join(List<String> strings) {
        String result = "";
        if (strings.size() > 0){
            List<String> allExceptFirstString = 
                new ArrayList<String>(strings);
            result += allExceptFirstString.remove(0);
            for (String string : allExceptFirstString) {
                result += delimiter + string;
            }
        }
        return result;
    }
}</td>
  </tr>
</table>


This sure doesn’t look simple, but it *was* easy to implement and is a small incremental change to our previous code. We know that our code does the right thing because all of our tests pass now and our most recent test didn’t pass before we wrote this code. 

This is the first time that we’ve had code that we might want to refactor. It’s safe to refactor because all of our code is covered by tests and all of those tests are passing. Here’s a slightly cleaner version of the code.

<table>
  <tr>
    <td>public class StringJoiner {
    private String delimiter;

    public StringJoiner(String delimiter) {
        this.delimiter = delimiter;
    }
    
    public String join(List<String> strings) {
        if (!strings.isEmpty()){
            String firstString = strings.get(0);
            List<String> remainingStrings = 
                strings.subList(1, strings.size());
            return firstString +           
                   concatenateWithDelimiter(otherStrings);
        }
        return "";
    }

    private String concatenateWithDelimiter(List<String> strings) {
        String result = "";
        for (String string : strings) {
            result += delimiter + string;
        }
        return result;
    }
}</td>
  </tr>
</table>


As a result of our disciplined practice of TDD, we have evidence that our code is correct and we were able to safely refactor it into code that is easier to read, extend, and test.

## Factorial Exercise - make 1 test pass at a time 

	link

	instructions

## Write your own tests

	give AC that correspond to specific tests

<table>
  <tr>
    <td>Given</td>
    <td>When</td>
    <td>Then</td>
  </tr>
  <tr>
    <td>I have $100 in my account</td>
    <td>I deposit $50</td>
    <td>I see that my account contains $150</td>
  </tr>
  <tr>
    <td>I have $100 in my account</td>
    <td>I withdraw $50</td>
    <td>I see that my account contains $50</td>
  </tr>
  <tr>
    <td>I have $50 in my account</td>
    <td>I withdraw $100</td>
    <td>I see that my withdrawal was unsuccessful
And I see that my account contains $50</td>
  </tr>
</table>


## Test Doubles & Stubs

	[Wikipedia](http://en.wikipedia.org/wiki/Test_double) provides a concise overview of different types of test doubles:

* [Test stub](http://en.wikipedia.org/wiki/Test_stubs) (used for providing the tested code with "indirect input")

* [Mock object](http://en.wikipedia.org/wiki/Mock_object) (used for verifying "indirect output" of the tested code, by first defining the expectations before the tested code is executed)

* [Test spy](http://en.wikipedia.org/w/index.php?title=Test_spy&action=edit&redlink=1) (used for verifying "indirect output" of the tested code, by asserting the expectations afterwards, without having defined the expectations before the tested code is executed)

* [Fake object](http://en.wikipedia.org/wiki/Fake_object) (used as a simpler implementation, e.g. using an in-memory database in the tests instead of doing real database access)

* [Dummy object](http://en.wikipedia.org/w/index.php?title=Dummy_object&action=edit&redlink=1) (used when a parameter is needed for the tested method but without actually needing to use the parameter)

## Mocks

Bill, edit for clarity & simplicity. Pictures and/or code?

Up to this point, we’ve test driven situations where we only care that we get the right return value from a method. In real object-oriented code, we care about object interactions. For example, if we tell our Car to accelerate we want it to give the Engine more fuel. An implementation of this might look like this:

	

	[Wikipedia](http://en.wikipedia.org/wiki/Mock_object) gives an overview of mock objects; especially useful when the real object:

* supplies [non-deterministic](http://en.wikipedia.org/wiki/Nondeterministic_algorithm) results (e.g., the current time or the current temperature);

* has states that are difficult to create or reproduce (e.g., a network error);

* is slow (e.g., a complete [database](http://en.wikipedia.org/wiki/Database), which would have to be initialized before the test);

* does not yet exist or may change behavior;

* would have to include information and methods exclusively for testing purposes (and not for its actual task).

For a more detailed explanation, see [Martin Fowler](http://martinfowler.com/articles/mocksArentStubs.html)’s essay exploring differences between mocks and stubs.

## Mockito

Mockito is a Java library that lets you mock and stub objects with impunity.  It provides two extraordinarily useful methods, *when...thenReturn *(for stubbing)*, *and *verify *(for mocking)*. *Check out the following examples, from the [Mockito homepage](https://code.google.com/p/mockito/):

**_when...thenReturn Example:_**

<table>
  <tr>
    <td>import static org.mockito.Mockito.*;
import org.pojava.datetime.DateTime;

DateTime mockDateTime = mock(DateTime.class);
when(mockDateTime.toString()).thenReturn("2013-04-08 16:33:17");
System.out.println(mockDateTime.toString());</td>
  </tr>
</table>


**_verify Example:_**

<table>
  <tr>
    <td>@Test
public void shouldSomething(){
LinkedList mockList = mock(LinkedList.class);
mockList.add("one");
mockList.clear();
verify(mockList).add(“one”);
verify(mockList).clear();
}</td>
  </tr>
</table>


In this example, Mockito is used to mock a LinkedList.  Even though the list is cleared, the mock lets you access which methods have been called on mockList.

## Mockito Screencast

## Patterns: Add example code

These are concepts/strategies that tend to lead to test that drive us to write testable and flexible code. You should think of them as recipes. They are guidelines to help you succeed when you first start writing tests. Over time you will learn when and where to improvise on these recipes.

Single Constructor

Mock Everything

Dependency Injection (screencast)

No Statics

## TDD Anti-patterns: Add example code

Here are some common pithy-named anti-patterns in TDD:

**Excessive Setup**

A test that requires a lot of work setting up in order to even begin testing. Sometimes several hundred lines of code is used to setup the environment for one test, with several objects involved, which can make it difficult to really ascertain what is tested due to the "noise" of all of the setup going on.

**The Giant**

A unit test that, although it is validly testing the object under test, can span thousands of lines and contain many many test cases. This can be an indicator that the system under tests is a [God Object](http://en.wikipedia.org/wiki/God_object)

**Generous Leftovers ****[[4**]](http://blog.james-carr.org/2006/11/03/tdd-anti-patterns/#joakim)

An instance where one unit test creates data that is persisted somewhere, and another test reuses the data for its own devious purposes. If the "generator" is ran afterward, or not at all, the test using that data will outright fail.

**The Dodger ****[[1**]](http://blog.james-carr.org/2006/11/03/tdd-anti-patterns/#frank)

A unit test which has lots of tests for minor (and presumably easy to test) side effects, but never tests the core desired behavior. Sometimes you may find this in database access related tests, where a method is called, then the test selects from the database and runs assertions against the result.

For more examples, read [James Carr’s whole article on TDD Anti-Patterns](http://blog.james-carr.org/2006/11/03/tdd-anti-patterns/)

1. TDD Wiki Article w/ edits

2. Explain patterns

3. DI / AllArgsConstructor

4. Mock all the things

5. AAA/GWT

6. [Fragile Tests]([http://xunitpatterns.com/Fragile%20Test.html](http://xunitpatterns.com/Fragile%20Test.html))

7. Do not change protection levels to make testable

8. Tests help you know when to move private methods to a new class

9. Examples

    1. Good examples

    2. Bad examples showing anti-patterns

    3. Examples of when to use mocks vs stubs

10. Screencast

11. Exercises

Ideas for interactive work:

	Provide examples of using mockito!

	Give examples - write these tests for this code

		very granular explanation of how tests are working

		here’s some code, test X, test Y, test Z

	MAke these tests pass in order

	Make screencasts of using mockito

# * * *
References

Test-Driven Development. In Wikipedia. Retrieved February 1, 2014, from http://en.wikipedia.org/wiki/Test-driven_development

* * *


TDD Wikipedia article

I think the Wikipedia article for TDD did a good job of providing a condensed summary of the theory, steps, and rationale for TDD.  While TDD By Example by Kent Beck had a lot more text, I essentially took away the same amount of information if not less than the much more concise Wikipedia article.  I think I would have had a better grasp of TDD with some more concrete examples using the tech platforms we would be using throughout TWU101.  More screencasts would have been helpful, in addition to at least one assignment for us to test drive or at least write tests for some of our simple exercises.  Having an assignment for TDD would allow us to start playing around and get a better feel for IntelliJ, JUnit, and possibly Mockito. 

I thought the Wikipedia article was pretty thorough and clear. Reading it straight through there did seem to be some repetition of ideas, which made it sort of verbose, but considering one would probably normally just reference a particular section I don't think this is a particularly bad thing.  I would perhaps expand the section on Code Visibility.  One of the more challenging aspects of adapting to TDD for me has been grappling with issues that seem to relate to this idea - i.e. wanting to use fields/methods in tests that are private.  It also seems like there is more to say about the more general ideas surrounding testing only public API's vs private implementations.  There were also several resources to which the article refers that I found particularly helpful, these were:

http://c2.com/cgi/wiki?TestDrivenDevelopment

http://blogs.msdn.com/b/jamesnewkirk/archive/2004/06/07/150361.aspx

http://xunitpatterns.com/Fragile%20Test.html

Reading the Wikipedia article is helpful, as it attempts to condense Kent Beck's book down to the essentials. Possibly, the less experienced people should focus on the upper half of the article, and maybe should skip everything below the section "Shortcomings", except the section on mocks. Unfortunately, the book (TDD by Example), for those without a CS background, is heavy going. You may want to consider a PowerPoint type presentation, or an outline with the important points organized and appropriately emphasized. A diagram of the flow is also nice, like slide 11 from this presentation (http://www.slideshare.net/vkarai/agile-test-driven-development-1265878). Also, the bullet points presented here (http://guide.agilealliance.org/guide/tdd.html) seem like a helpful way to communicate essentials. Also, I think having a one-page overview as a guide to reading the book is a good idea. Truly, being walked through your simple github examples, like StringReverser, and TDD examples are the best way to learn. The videos in the modules were also great, especially the one on writing a method to compute the Fibonacci numbers. I think a document with all your best “recipes” and points to remember is a good accompaniment to the explanations, and a good way to reinforce learning. 

This Wikipedia article made reasonable sense to me now, but I think that if I were reading it with no prior knowledge of TDD, it would go over my head a lot. There is a lot of really good information here, including some useful tips for "best practices" and “practices to avoid” - several of these are things that I have encountered over the course of this week, and it's nice to see them explicitly described. However, all of this goes by very quickly, and without concrete examples to illustrate the principles, it would be difficult for me to get any good sense of how to do TDD just from the article. This article goes through the process—write a failing test, make it pass, refactor—but doesn't ever really go into much detail about how to complete the first of these steps to begin with. That being the case, the obvious biggest change would be to include a lot of concrete, simple examples which clearly demonstrate the basic process of writing failing tests, as well as the procedure of making them pass and refactoring. There are some examples of tests in the linked article on Unit Testing, which might be a helpful article to point people to, though I don't think that it alone would offer enough illustration of the TDD principles. On top of this, I would have appreciated seeing examples of poorly-written tests as well as good tests, in order to help me anticipate and recognize when I was going wrong as I started attempting TDD myself.

I thought that the article gave a slightly better description of when mock objects are useful than we saw in Kent Beck's book, but it still doesn't state clearly enough (for a beginner, at least) the rule that you should mock out every class/dependency other than the class under test. Having that explicitly spelled out for me would have helped me to understand earlier when and why mocks are used. Additionally, I was not familiar with the term "dependency" prior to starting at ThoughtWorks, so any explanation that relied solely on phrases like “dependency injection” and “you should mock out all dependencies” would not have been very useful to me without some extra clarification.

The article briefly comments that TDD helps create smaller, more focused classes, and that this is a good thing, but it would be more helpful if this were given a little more airtime. One of the things that I have struggled with as I've started doing TDD has been handling public vs. private methods, and the idea that "if you're trying to test a lot private methods, maybe you should introduce a new class" was one that became clear to me relatively late in the week. Making smaller methods and classes was a common theme, but it took a while before I started to get a sense of exactly how to accomplish this.

I feel like I learned a lot about TDD during the pairing interview. We were refactoring my Mars rover program, and I can't recall the exact details, but I think we were adding a class, and my two JC pairs guided me into writing the tests first.

I think one of my partners wrote a simple unit test, and then challenged me to make it pass in the most obvious way possible. And after that, we tried to come up with another test that would force us to make it right. They introduced the idea of ping pong coding, and also used a lot of keyboard shortcuts (which I used to use very few of) and I was very impressed with the way they worked and talked about things.

Overall, I felt kind of enlightened by what I learned during that interview, and this week has been an extension of that. I wonder if all the JCs in our class got their hands-on experience in TDD during their pairing session like I did, cause I thought it was really helpful in getting ready for this week. Also, my experience during the interview definitely helped me get through and take more from the book.

I really liked the stub and mock examples that were used at the very start of TW101. My one suggestion for now would have to do with that:

It's a time issue, but I think it's worth considering to build up the stub and the mock examples from scratch, in a way similar to how we did the football project on Sunday, rather than just walking through the code.

TDD Article

When question that I found myself asking when first learning TDD were the 'actual steps'. Everyone knows red green refactor. But I think what I really wanted to understand was more like:

•	write the desired call/action in main()/production ("what", not “how”)

•	ie. list.printList();

•	then write the code that tests the desired result

•	ie. public void shouldPrintList

•	run test (which fails)

•	write code in production to make test pass ("how")

•	run test (which passes)

•	refactor (make it pretty)

Sometimes, knowing whether to start in the test code versus the main code can be hard when just starting TDD. As I read through the Wikipedia article I recognized a lot of terminology that we discussed in class. In that way, reading the article was like a refresher. However, reading this article beforehand could also provide a precursor to topics that could then be discussed more deeply in class. Not sure which way is best. This is a long article with a lot of technical terms that my seem overwhelming but I think it is more straightforward than the TDD book we were originally assigned.

To better understand TDD, I personally would have liked some help interpreting user stories and writing test cases from those stories. An example exercise I think I would have liked would have been to walk through as a group examining a user story and as a group describing how we should think about what test to make, even just in naming method headers. Perhaps doing such an exercise twice to four times would have let me have a better mindset about what tests are good: what test is too small, what test is too robust, what test is out of scope, etc.

I think that the cycle description is very helpful. If that were paired with a TDD/java version of the Everyday Rails Testing with Rspec repo it would be perfect (for me at least). Basically it shows step changes between commits. 

The issue I have is "keep the unit small". What is consider small seems to change wildly between the articles I've read and tutorials I've followed. 

And examples of anti-patterns/best practices would also be helpful. 

Project Basic TDD Screencast

Project Mockito Screencast

Watch [Danilo Sato’s screencasts](http://www.dtsato.com/blog/) (keyboard shortcut)

Create code example

