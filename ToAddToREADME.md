## Mocks & Stubs

### Test Doubles
Up to this point, we’ve test driven situations where the class we are testing does not depend on any other class and we
only care that we get the right return value from a method. In real life we often have:

* `void` methods which have no return value for us to assert against
* methods that don't take any parameters 
* code that calls methods that is bad for our tests (e.g. current time, `System.out` or database)

> **System.out.println()**
> `System.out` is a public static variable that is of type PrintStream. That means that we could refactor 
> `System.out.println()` to 
> ```
> PrintStream printStream = System.out;
> printStream.println();
> ```
> string "a,b,c”. Note that there is not a leading or trailing comma.


These are all situations where we want our tests to behave differently than our production code without changing our 
production code when we test it. 

How can we change the behavior of our code without changing our code?

We can accomplish this by changing the behavior of the methods that our code calls. What if `println()` did something 
different when we call it while testing? In our tests it could tell us what it tried to print (without actually printing
 anything) and in our production code we could have it print normally.
 
A simple way to do this is to give 

### Testing `void` methods

## Breaking Dependencies
### Sensing & Separation

We break dependencies:
 * so we can *sense* when we can't access values our code computes
 * to *separate* when we can't even get a piece of code into a test harness to run.

#### Sensing
 * *verify*
 * getters and non-private fields

#### Separation
 * *when*
 * avoid using real resources
 * helps write maintainable tests


	[Wikipedia](http://en.wikipedia.org/wiki/Test_double) provides a concise overview of different types of test doubles:

* [Test stub](http://en.wikipedia.org/wiki/Test_stubs) (used for providing the tested code with "indirect input")

* [Mock object](http://en.wikipedia.org/wiki/Mock_object) (used for verifying "indirect output" of the tested code, by first defining the expectations before the tested code is executed)

* [Test spy](http://en.wikipedia.org/w/index.php?title=Test_spy&action=edit&redlink=1) (used for verifying "indirect output" of the tested code, by asserting the expectations afterwards, without having defined the expectations before the tested code is executed)

* [Fake object](http://en.wikipedia.org/wiki/Fake_object) (used as a simpler implementation, e.g. using an in-memory database in the tests instead of doing real database access)

* [Dummy object](http://en.wikipedia.org/w/index.php?title=Dummy_object&action=edit&redlink=1) (used when a parameter is needed for the tested method but without actually needing to use the parameter)

## Mocks

Bill, edit for clarity & simplicity. Pictures and/or code?




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

&lttable&gt
  &lttr&gt
    &lttd&gtimport static org.mockito.Mockito.*;
import org.pojava.datetime.DateTime;

DateTime mockDateTime = mock(DateTime.class);
when(mockDateTime.toString()).thenReturn("2013-04-08 16:33:17");
System.out.println(mockDateTime.toString());&lt/td&gt
  &lt/tr&gt
&lt/table&gt


**_verify Example:_**

&lttable&gt
  &lttr&gt
    &lttd&gt@Test
public void shouldSomething(){
LinkedList mockList = mock(LinkedList.class);
mockList.add("one");
mockList.clear();
verify(mockList).add("one”);
verify(mockList).clear();
}&lt/td&gt
  &lt/tr&gt
&lt/table&gt


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

Reading the Wikipedia article is helpful, as it attempts to condense Kent Beck's book down to the essentials. Possibly, the less experienced people should focus on the upper half of the article, and maybe should skip everything below the section "Shortcomings", except the section on mocks. Unfortunately, the book (TDD by Example), for those without a CS background, is heavy going. You may want to consider a PowerPoint type presentation, or an outline with the important points organized and appropriately emphasized. A diagram of the flow is also nice, like slide 11 from this presentation (http://www.slideshare.net/vkarai/agile-test-driven-development-1265878). Also, the bullet points presented here (http://guide.agilealliance.org/guide/tdd.html) seem like a helpful way to communicate essentials. Also, I think having a one-page overview as a guide to reading the book is a good idea. Truly, being walked through your simple github examples, like StringReverser, and TDD examples are the best way to learn. The videos in the modules were also great, especially the one on writing a method to compute the Fibonacci numbers. I think a document with all your best "recipes” and points to remember is a good accompaniment to the explanations, and a good way to reinforce learning.

This Wikipedia article made reasonable sense to me now, but I think that if I were reading it with no prior knowledge of TDD, it would go over my head a lot. There is a lot of really good information here, including some useful tips for "best practices" and "practices to avoid” - several of these are things that I have encountered over the course of this week, and it's nice to see them explicitly described. However, all of this goes by very quickly, and without concrete examples to illustrate the principles, it would be difficult for me to get any good sense of how to do TDD just from the article. This article goes through the process—write a failing test, make it pass, refactor—but doesn't ever really go into much detail about how to complete the first of these steps to begin with. That being the case, the obvious biggest change would be to include a lot of concrete, simple examples which clearly demonstrate the basic process of writing failing tests, as well as the procedure of making them pass and refactoring. There are some examples of tests in the linked article on Unit Testing, which might be a helpful article to point people to, though I don't think that it alone would offer enough illustration of the TDD principles. On top of this, I would have appreciated seeing examples of poorly-written tests as well as good tests, in order to help me anticipate and recognize when I was going wrong as I started attempting TDD myself.

I thought that the article gave a slightly better description of when mock objects are useful than we saw in Kent Beck's book, but it still doesn't state clearly enough (for a beginner, at least) the rule that you should mock out every class/dependency other than the class under test. Having that explicitly spelled out for me would have helped me to understand earlier when and why mocks are used. Additionally, I was not familiar with the term "dependency" prior to starting at ThoughtWorks, so any explanation that relied solely on phrases like "dependency injection” and "you should mock out all dependencies” would not have been very useful to me without some extra clarification.

The article briefly comments that TDD helps create smaller, more focused classes, and that this is a good thing, but it would be more helpful if this were given a little more airtime. One of the things that I have struggled with as I've started doing TDD has been handling public vs. private methods, and the idea that "if you're trying to test a lot private methods, maybe you should introduce a new class" was one that became clear to me relatively late in the week. Making smaller methods and classes was a common theme, but it took a while before I started to get a sense of exactly how to accomplish this.

I feel like I learned a lot about TDD during the pairing interview. We were refactoring my Mars rover program, and I can't recall the exact details, but I think we were adding a class, and my two JC pairs guided me into writing the tests first.

I think one of my partners wrote a simple unit test, and then challenged me to make it pass in the most obvious way possible. And after that, we tried to come up with another test that would force us to make it right. They introduced the idea of ping pong coding, and also used a lot of keyboard shortcuts (which I used to use very few of) and I was very impressed with the way they worked and talked about things.

Overall, I felt kind of enlightened by what I learned during that interview, and this week has been an extension of that. I wonder if all the JCs in our class got their hands-on experience in TDD during their pairing session like I did, cause I thought it was really helpful in getting ready for this week. Also, my experience during the interview definitely helped me get through and take more from the book.

I really liked the stub and mock examples that were used at the very start of TW101. My one suggestion for now would have to do with that:

It's a time issue, but I think it's worth considering to build up the stub and the mock examples from scratch, in a way similar to how we did the football project on Sunday, rather than just walking through the code.

TDD Article

When question that I found myself asking when first learning TDD were the 'actual steps'. Everyone knows red green refactor. But I think what I really wanted to understand was more like:

•	write the desired call/action in main()/production ("what", not "how”)

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

