# Test Driven Development in Java

## Introduction

These lessons will teach you the basics of Test Driven Development (TDD) in Java, using JUnit, Mockito, and IntelliJ.

We’re assuming that we don’t need to convince you why you want to do TDD and we’ll only touch lightly on the principles
of TDD. Instead we’ll be focusing on the what and how.

## What is TDD?

TDD is the practice of writing a small amount of code (a unit test) that describes the new behavior you wish to add to
your program before you implement the behavior itself.

### Unit Tests

You can think of unit tests as tiny programs we write to verify that the methods in our classes do what we expect them to do.

## The TDD Cycle

The following sequence is based on the book *[Test-Driven Development by Example](http://en.wikipedia.org/wiki/Test-Driven_Development_by_Example)*.

The basics steps for this process look like this:

1. Write a small failing unit test

2. Make this new test pass in the simplest way possible

3. Clean up any messes we created

![image](https://github.com/BillSchofield/TDDIntro/blob/master/src/common/images/TDDCycle.png?raw=true)

### Add a test

In test-driven development, each new feature begins with writing a test. This test must inevitably fail because it is
written before the feature has been implemented. (If it does not fail, then either the proposed "new" feature already
exists or the test is defective.) To write a test, the developer must clearly understand the feature's specification
and requirements. The developer can accomplish this through [use cases](http://en.wikipedia.org/wiki/Use_case) and
[user stories](http://en.wikipedia.org/wiki/User_story) to cover the requirements and exception conditions, and can
write the test in whatever testing framework is appropriate to the software environment. This could also be a
modification of an existing test. This is a differentiating feature of test-driven development versus writing unit tests
*after* the code is written: it makes the developer focus on the requirements *before* writing the code, a subtle
but important difference.

### Run all tests and see if the new one fails

This validates that the [test harness](http://en.wikipedia.org/wiki/Test_harness) is working correctly and that the new
test does not mistakenly pass without requiring any new code. This step also tests the test itself; it rules out the
possibility that the new test always passes, and therefore is worthless. The new test should also fail for the expected
reason. This increases confidence (though does not guarantee) that it is testing the right thing, and passes only in
intended cases.

### Write some code

The next step is to write code that causes the test to pass. The new code written at this stage is not perfect, and may,
for example, pass the test in an inelegant way. That is acceptable because later steps improve and hone it.

At this point, the only purpose of the written code is to pass the test; no further (and therefore untested)
functionality should be predicted and 'allowed for' at any stage.  This prevents unnecessary and unspecified code from
being written, helping avoid [YAGNI](http://en.wikipedia.org/wiki/You_aren't_gonna_need_it) functionality.

### Run tests

If all test cases now pass, the programmer can be confident that the code meets all the tested requirements. This is a
good point from which to begin the final step of the cycle.

### Refactor code

Now the code should be cleaned up. Move code to where it logically belongs. Remove duplication. Make sure variable and
method names represent their current use. Clarify constructs that might be misinterpreted. Use Kent Beck's four rules of
simple design to guide you, as well as anything else you know about writing clean code. By re-running test cases, you
can be confident that [refactoring](http://en.wikipedia.org/wiki/Code_refactoring) is not damaging any existing
functionality.

The concept of removing duplication is an important aspect of any software design. In this case it also applies to
removing duplication between test code and production code—for example
[magic numbers or strings](http://en.wikipedia.org/wiki/Magic_number_(programming)) repeated in both to make the test
pass in the "Write some code" step.

### Repeat

Starting with another new test, repeat the cycle to push forward the functionality. The size of the steps should always
be small, with as few as 1 to 10 edits between each test run. If new code does not rapidly satisfy a new test, or other
tests fail unexpectedly, the programmer should [undo](http://en.wikipedia.org/wiki/Undo) or revert in preference to
excessive [debugging](http://en.wikipedia.org/wiki/Debugging).
[Continuous integration](http://en.wikipedia.org/wiki/Continuous_integration) helps by providing revertible checkpoints.
When using external libraries do not make increments so small that they merely testing the library itself, unless there
is some reason to believe that the library is buggy or not sufficiently feature-complete to serve all the needs of the
main program being written.

## Unit Tests

As we mentioned above, unit tests are small programs that we use to verify the correctness of our "production" code.
The word *unit* refers to a subdivision of the overall program. While others might consider *unit* to mean a class or
package, we will only be unit testing at the method level.

Before you start writing a unit test you should know what behavior you want to verify. For instance, you might have a
method that returns the plural version of a word. In English, the way we pluralize most words is by adding the letter
‘s’ to the end of the word. That sounds like a great first test case.

Once you know what behavior you want to verify you can name your test. A great format for test names is,
`should&ltexpected behavior&gtWhen&ltsituation that behavior depends on&gt`. In our pluralizing example, the expected
behavior is ‘add an s’ and the situation is ‘normal word’. That means that we could name out test
`shouldAddSWhenWordIsNormal`. Since it’s not necessarily clear what it means for a word to be ‘normal’, we could also
name the test `shouldAddS` or `shouldAddSToWord`.

Once you know the behavior you want to verify and the method where you expect add that behavior, you can start writing
your test. We’ll show you how to do this in JUnit.

## JUnit

JUnit is a popular Java unit testing framework. We’re going to use JUnit to create our TDD unit tests.

JUnit example:

``` java
public class PluralizerTests {
    ...

    @Test
    public void shouldAddSWhenWordIsNormal() {
    // Arrange our objects
    Pluralizer pluralizer = new Pluralizer();

    // Action we are testing
    String result = pluralizer.pluralize("Cat");

    // Assert that the action caused the expected result
    assertThat(result, is("Cats"));
    }
    ...
}
```

### Test Classes

We call the class that we are testing the *class under test*. In the example above, the class under test is
**`Pluralizer`**. All of the unit tests for the *class under test* will live inside a test class named:
**`&lt&ltclass under test&gtTests.java`**.

### Anatomy of a Unit Test

There are three sections to every unit test. One set of names for these sections is: Arrange, Action, Assert.
Another is: Given, When, Then.

#### Arrange/Given 

This is where we set the stage for our scenario. That means that we create all of the objects we need for the test in
this section. While arranging happens at the top of our test, we often make changes here after working on the other two
sections.

#### Action/When

The Action/When section is where we call the method that we are testing (the Action). This should usually be a single
method call.

#### Assert/Then

We verify that the method under test caused the right thing to happen in Assert/Then section of our tests. If you feel
like you need more than one assert you should probably split your test.

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

Here’s an example of Test Driving a **`StringJoiner`** class whose job is to *Join* strings.

> **Join** -
>Joining a list of strings means creating a single new string by concatenating the string in list together with a
> delimiter between them. For instance, joining the strings {"a", "b”, "c”} on the delimiter ",” would result in the
> string "a,b,c”. Note that there is not a leading or trailing comma.

1) Create all of the test scaffolding and get it to compile (name your test something ugly).

``` java
public class StringJoinerTests {
    @Test
    public void shouldFooWhenBar() {
    }
}
```

2 &amp 3) Next, create an instance of the class you are testing and call the method. You should type out the name of the
class and method even if they don’t exist yet. In the example below, assume that the class **`StringJoiner`** doesn’t
exist yet.

``` java
public class StringJoinerTests {
    @Test
    public void shouldFooWhenBar() {
        String result = new StringJoiner().join();
    }
}
```

Because **`StringJoiner`** doesn’t exist yet, our IDE highlights it in red. In IntelliJ we can click on the class name, 
press Alt-Enter and choose the option **`Create Class ‘StringJoiner’`**. This will automatically create the class for 
you. After the class is created our test will look like this...

``` java
public class StringJoinerTests {
    @Test
    public void shouldFooWhenBar() {
        String result = new StringJoiner().join();
    }
}
```

The write method is red because it’s not implemented yet. Hit Alt-Enter and choose **`Create Method ’join’`**. Now we’re 
calling the method we want to test. Here are some useful questions we can ask:

* What is the smallest piece of new behavior that we can add? 

* What change would it cause? 

These questions should lead us to add an assert that verifies that we got the correct result from joining some strings. 
Right now the **`join`** method returns **`null`**. Let’s add the behavior that causes **`join`** to return the empty string when 
the list that is passed in is empty.

Wait a minute, we aren’t passing a list of strings into the **`join`** method yet. We also suddenly have enough 
information to name our test. And we want to add an assert too. When you have more than one thing that could be your 
next change, write down the options and do them one at at time. Here’s our current ToDo list:

1. Pass list of strings to join

2. Add assert to test

3. Rename this test to ???

4) Let’s pass an empty list into **`join`** first.

``` java
public class StringJoinerTests {
    @Test
    public void shouldFooWhenBar() {
        List&ltString&gt strings = new ArrayList&ltString&gt();
        String result = new StringJoiner().join(strings);
    }
}
```


5) Now we can add our assert that verifies that we got an empty string back.

``` java
public class StringJoinerTests {
    @Test
    public void shouldFooWhenBar() {
        List&ltString&gt strings = new ArrayList&ltString&gt();
        String result = new StringJoiner().join(strings);
        assertThat(result, is(""));
    }
}
```


6) Now that we really understand what we are testing we can rename our test. Normally you will name your test **before** 
you start writing it. You should only name it later when you have trouble understanding what you want to test. Let’s 
rename the test based on what we know about the purpose of our test.

``` java
public class StringJoinerTests {
    @Test
    public void shouldJoinIntoAnEmptyStringWhenListIsEmpty() {
        List&ltString&gt strings = new ArrayList&ltString&gt();
        String result = new StringJoiner().join(strings);
        assertThat(result, is(""));
    }
}
```

This is a complete unit test. Let’s split it up to clarify what the three sections of the test are.

``` java
public class StringJoinerTests {
    @Test
    public void shouldJoinIntoAnEmptyStringWhenListIsEmpty() {
        // Arrange
        StringJoiner joiner = new StringJoiner();
        List&ltString&gt strings = new ArrayList&ltString&gt();

        // Action
        String result = joiner.join(strings);

        // Assert
        assertThat(result, is(""));
    }
}
```

We can run the test and watch it fail by clicking anywhere in the test file and hitting Ctrl-Shift-F10.

Now we want to make it pass by writing the simplest code possible. This is how we can make the test pass:

``` java
public class StringJoiner {
    public String join(List&ltString&gt strings) {
        return "";
    }
}
```

This is our first passing test! If there was anything for us to refactor we would do so now. Now let’s write more tests.
We’ll move a little faster now.

``` java
    @Test
    public void shouldJoinIntoTheStringWhenListIsOneString(){
        List&ltString&gt strings = new ArrayList&ltString&gt();
        String aString = "A String";
        strings.add(aString);
        StringJoiner joiner = new StringJoiner();

        String result = joiner.join(strings);

        assertThat(result, is(aString));
    }
```

This is a much more interesting test than the first one. There’s a lot going on in the Arrange section, although most
of it is just adding a string to the list. Other than the change to how we arrange the objects this test is mostly the 
same as the first one. The test fails as expect and this is a simple way to make it pass.

``` java
public class StringJoiner {
    public String join(List&ltString&gt strings) {
        if (strings.size() &gt 0){
            return strings.get(0);
        }
        return "";
    }
}
```


Now we want to run both of our tests. A simple way to do this is to click anywhere in the test file that is not inside 
of a method and hit Ctrl-Shift-F10. Now both tests pass and it’s time to think about refactoring. There’s nothing 
obvious to refactor in **`StringJoiner`**, but there’s a lot of duplication in our test class. After removing comments 
and blank lines, it looks like this:

``` java
public class StringJoinerTests {
    @Test
    public void shouldJoinIntoAnEmptyStringWhenListIsEmpty(){
        List&ltString&gt strings = new ArrayList&ltString&gt();
        StringJoiner joiner = new StringJoiner();
        String result = joiner.join(strings);
        assertThat(result, is(""));
    }
    @Test
    public void shouldJoinIntoTheStringWhenListIsOneString(){
        List&ltString&gt strings = new ArrayList&ltString&gt();
        String aString = "A String";
        strings.add(aString);
        StringJoiner joiner = new StringJoiner();
        String result = joiner.join(strings);
        assertThat(result, is(aString));
    }
}
```

The blue colored lines are exactly the same in both tests. Let’s fix this while our tests are passing so we can have 
confidence that we didn’t break anything. We can move change these local variables into instance variables which we 
initialize in setup method like this:

``` java
public class StringJoinerTests {
    private List&ltString&gt strings;
    private StringJoiner joiner;
    @Before
    public void setUp() throws Exception {
        strings = new ArrayList&ltString&gt();
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
}
```


Note that we removed the **`result`** variable to improve readability.

There’s also a new method call **`setUp`** which has the **`@Before`** annotation. Any method that is marked with 
**`@Before`** will be executed before each test in that same class. This allows us to reset the strings and joiner 
instance variables so that they don’t allow the actions of one test to affect another.

So far we have taken such small steps that out **`StringJoiner`** class doesn’t do much. This is normal for TDD, we’re 
implementing the behavior we want in very small slices but they will quickly add up to everything we need. Here’s our 
next test.

``` java
    @Test
    public void shouldContainBothStringsWhenListIsTwoStrings(){
        strings.add("A");
        strings.add("B");
        assertThat(joiner.join(strings),                     
            both(containsString("A")).
            and(containsString("B")));
    }
```    

The assert in this test is more complex than in previous tests. **`ContainsString`** verifies that the result of join 
contains a certain string (in this case "A" or "B”). The **`both/and`** construct means that both **`containsString`** 
verifications must be true for the assert to pass. 

When we run all of our tests, we’re happy to see that this new test fails and all of our old tests pass. Now we need to 
make the new test pass.

``` java
public class StringJoiner {
    public String join(List&ltString&gt strings) {
        String result = "";
        for (String string : strings) {
            result += string;
        }
        return result;
    }
}
```

This makes all of our tests pass. Great! So far our **`StringJoiner`** joins all of the strings together, but it doesn’t
even know what a delimiter is, much less how to put it between the strings in the list. Our new test should fix that…

``` java
    @Test
    public void shouldPutDelimiterBetweenStrings(){
        StringJoiner joinerWithDelimiter = new StringJoiner(",");
        strings.add("A");
        strings.add("B");
        assertThat(joinerWithDelimiter.join(strings), is("A,B"));
    }
```


Our **`StringJoiner`** now knows about delimiters and it’s constructor takes one as a parameter. Because our constructor
changed we have to update all of the places that we create a new **`StringJoiner`**. Fortunately, there is only one
other **`new StringJoiner`** in our tests because we removed duplication early on and moved creation of our
**`StringJoiner`** into the **`setUp`** method. Now we need to figure out the simplest way to make all of our tests pass.

``` java
public class StringJoiner {
    private String delimiter;

    public StringJoiner(String delimiter) {
        this.delimiter = delimiter;
    }

    public String join(List&ltString&gt strings) {
        String result = "";
        if (strings.size() &gt 0){
            List&ltString&gt allExceptFirstString = 
                new ArrayList&ltString&gt(strings);
            result += allExceptFirstString.remove(0);
            for (String string : allExceptFirstString) {
                result += delimiter + string;
            }
        }
        return result;
    }
}
```


This sure doesn’t look simple, but it *was* easy to implement and is a small incremental change to our previous code.
We know that our code does the right thing because all of our tests pass now and our most recent test didn’t pass before
we wrote this code.

This is the first time that we’ve had code that we might want to refactor. It’s safe to refactor because all of our code
is covered by tests and all of those tests are passing. Here’s a slightly cleaner version of the code.

``` java
public class StringJoiner {
    private String delimiter;

    public StringJoiner(String delimiter) {
        this.delimiter = delimiter;
    }
    
    public String join(List&ltString&gt strings) {
        if (!strings.isEmpty()){
            String firstString = strings.get(0);
            List&ltString&gt remainingStrings = 
                strings.subList(1, strings.size());
            return firstString +           
                   concatenateWithDelimiter(otherStrings);
        }
        return "";
    }

    private String concatenateWithDelimiter(List&ltString&gt strings) {
        String result = "";
        for (String string : strings) {
            result += delimiter + string;
        }
        return result;
    }
}
```


As a result of our disciplined practice of TDD, we have evidence that our code is correct and we were able to safely
refactor it into code that is easier to read, extend, and test.

## Factorial Exercise - make 1 test pass at a time 

	link

	instructions

## Write your own tests

	give AC that correspond to specific tests

&lttable&gt
  &lttr&gt
    &lttd&gtGiven&lt/td&gt
    &lttd&gtWhen&lt/td&gt
    &lttd&gtThen&lt/td&gt
  &lt/tr&gt
  &lttr&gt
    &lttd&gtI have $100 in my account&lt/td&gt
    &lttd&gtI deposit $50&lt/td&gt
    &lttd&gtI see that my account contains $150&lt/td&gt
  &lt/tr&gt
  &lttr&gt
    &lttd&gtI have $100 in my account&lt/td&gt
    &lttd&gtI withdraw $50&lt/td&gt
    &lttd&gtI see that my account contains $50&lt/td&gt
  &lt/tr&gt
  &lttr&gt
    &lttd&gtI have $50 in my account&lt/td&gt
    &lttd&gtI withdraw $100&lt/td&gt
    &lttd&gtI see that my withdrawal was unsuccessful
And I see that my account contains $50&lt/td&gt
  &lt/tr&gt
&lt/table&gt
