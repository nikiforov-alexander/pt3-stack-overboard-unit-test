## Techdegree project 3
### Unit testing of stack overboard
1.  Create a new Test Fixture for the User model in a separate but 
    same package test directory structure.
    <hr>
    Test directory is created in `src/test`. `UserTest.java` lies into
    `src/test/java/com/teamtreehouse/techdegree/overboard/model`. 
    <hr>
2.  Write a test to ensure that the questioner’s reputation goes up 
    by 5 points if their question is upvoted. 
    <hr>
    See test method 
    `questionersReputationGoesUpByFiveWhenTheirQuestionIsUpVoted()`
    <hr>
3.  Write a test to assert that the answerer’s reputation goes up by 
    10 points if their answer is upvoted.
    <hr>
    See test method
    `answererReputationGoesUpByTenIfAnswerIsUpVoted()`
    <hr>
4.  Write a test that that proves that having an answer accepted 
    gives the answerer a 15 point reputation boost.
    <hr>
    See test method 
    `havingAnswerAcceptedGivesAnswererPlusFifteenRep()`
    <hr>
5.  Using a test, ensure that voting either up or down is not 
    allowed on questions or answers by the original author, you know 
    to avoid gaming the system. Ensure the proper exceptions are being 
    thrown.
    <hr>
    There are 4 tests methods, checking up/down vote on answer and
    question accordingly:
    - `whenQuestionerUpvotesHisQuestionVotingExceptionIsThrown()`
    -  `whenQuestionerDownVotesHisQuestionVotingExceptionIsThrown()`
    - `whenQuestionerUpvotesHisAnswerVotingExceptionIsThrown()`
    - `whenQuestionerDownVotesHisAnswerVotingExceptionIsThrown()`
    
    Testing on down voting showed that method
    `downVote(Post post)` in `User.class` doesn't 
    throw `VotingException`. That's why I added necessary code 
    to `downVote(Post post)`, so that tests pass through.
    <hr>
6.  Write a test to make sure that only the original questioner 
    can accept an answer. Ensure the intended messaging is being 
    sent to back to the caller.
    <hr>
    Test, that checks that `AnswerAcceptanceException` is thrown, when
    other user is trying to accept the answer is:
    `whenOtherUserIsTryingToAcceptAnswerAnswerAcceptanceExceptionIsThrown()`
    
    Test, that checks that answer is accepted when questioner accepts
    it, is:
    `questionerAcceptingAnswerToHisQuestionSetsAnswerIsAccepted()`
    <hr>
    
### Extra Credit
6.  Create additional objects which can be shared across tests.
    <hr>
    In all tests I shared across from 1 to 4 member variables,
    initialized in `setUp()` method: 
    <hr>
7.  Reviewing the User.getReputation method may expose some code that 
    is not requested to be tested in the Meets Project instructions. 
    Write the missing test.
    <hr>
    Missing tests I've found were: check the questioner reputation, when 
    someone is downvoting his question. It is checked in this test:
        - `downVotingQuestionDoesNotAffectQuestionerReputation()`
    
    And also what happens with reputation if someone downvotes user's
    answer:
        - `downVotingAnswerReducesAnswererReputationByMinusOne()`
    <hr>
## Welcome to Stack Overboard

This is a WIP (Work In Progress) but the basic idea is this:

```java
// A board is a topic area on a bulletin board
Board board = new Board("Java");

// Create new users from the board
User alice = board.newUser("alice");
User bob = board.newUser("bob");
User charles = board.newUser("charles");

// Users create questions
Question question = alice.askQuestion("What is a String?");
// They can be upvoted
bob.upVote(question);

// Users can also answer questions
Answer answer = bob.answerQuestion(question, "It is a series of characters, strung together...");
// Answers can be upvoted
alice.upVote(answer);

// and downvoted
charles.downVote(answer);
charles.downVote(answer);

// and accepted by the person who asked the question
alice.accept(answer);

// There is then reputation
System.out.println("Alice: " + alice.getReputation()); // Alice's question got upvoted so this prints Alice: 5
System.out.println("Bob: " + bob.getReputation()); // Bob's answer got upvoted (10) and his answer was accepted (15)
                                                   // so this prints Bob: 25
```

## TODO
[x] TESTS!
[ ] Use a data store!
[ ] Build the website surrounding this awesome model
