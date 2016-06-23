package com.teamtreehouse.techdegree.overboard.model;

import com.teamtreehouse.techdegree.overboard.exc.AnswerAcceptanceException;
import com.teamtreehouse.techdegree.overboard.exc.VotingException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class UserTest {
    private Board mBoard;
    private User mQuestionerUser;
    private User mAnswererUser;
    private User mOtherUser;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        mBoard = new Board("topic");
        mQuestionerUser = mBoard.createUser("questioner");
        mAnswererUser = mBoard.createUser("answerer");
        mOtherUser = mBoard.createUser("other");
    }

    @Test
    public void questionersReputationGoesUpByFiveWhenTheirQuestionIsUpVoted()
            throws Exception {
        // Given board, questioner and other user
        int initialQuestionerReputation = mQuestionerUser.getReputation();
        // When question is asked and then upvoted by other user
        Question question = mQuestionerUser.askQuestion("question");
        mOtherUser.upVote(question);
        // Then questioner reputation should go up by 5 pts
        assertEquals(initialQuestionerReputation + 5,
                mQuestionerUser.getReputation());
    }

    @Test
    public void answererReputationGoesUpByTenIfAnswerIsUpVoted()
            throws Exception {
        // Given board, two users: answerer, and questioner
        int initialAnswererReputation = mAnswererUser.getReputation();
        // When questioner asks question, answerer answers and questioner
        // upvotes
        Question question = mQuestionerUser.askQuestion("question");
        Answer answer = mAnswererUser.answerQuestion(question,"answer");
        mQuestionerUser.upVote(answer);
        // Then answerer's reputation goes up by 10
        assertEquals(initialAnswererReputation + 10,
                mAnswererUser.getReputation());
    }

    @Test
    public void havingAnswerAcceptedGivesAnswererPlusFifteenRep()
            throws Exception {
        // Given board, answerer and questioner
        int initialAnswererReputation = mAnswererUser.getReputation();
        // When questioner asks question, answerer answers and
        // questioner accepts
        Question question = mQuestionerUser.askQuestion("question");
        Answer answer = mAnswererUser.answerQuestion(question,"answer");
        mQuestionerUser.acceptAnswer(answer);
        // Then answerer's reputation goes up by 15
        assertEquals(initialAnswererReputation + 15,
                mAnswererUser.getReputation());
    }
    @Test
    public void whenQuestionerUpvotesHisQuestionVotingExceptionIsThrown()
            throws Exception {
        // Given board and questioner
        // When questioner asks question and upvotes his own question
        // Then VotingException is thrown
        Question question = mQuestionerUser.askQuestion("question");
        expectedException.expect(VotingException.class);
        mQuestionerUser.upVote(question);
    }
    @Test
    public void whenQuestionerDownVotesHisQuestionVotingExceptionIsThrown()
            throws Exception {
        // Given board and questioner
        // When questioner asks question and downvotes his own question
        // Then VotingException is thrown
        Question question = mQuestionerUser.askQuestion("question");
        expectedException.expect(VotingException.class);
        mQuestionerUser.downVote(question);
    }

    @Test
    public void whenQuestionerUpvotesHisAnswerVotingExceptionIsThrown()
        throws Exception {
        // Given board and questioner
        // When questioner asks question, answers it and upvotes his own answer
        // Then VotingException should be thrown
        Question question = mQuestionerUser.askQuestion("question");
        Answer answer = mQuestionerUser.answerQuestion(question, "answer");
        expectedException.expect(VotingException.class);
        mQuestionerUser.upVote(answer);
    }

    @Test
    public void whenQuestionerDownVotesHisAnswerVotingExceptionIsThrown()
            throws Exception {
        // Given board and questioner
        // When questioner asks question, answers it and downvotes
        // his own answer
        // Then VotingException should be thrown
        Question question = mQuestionerUser.askQuestion("question");
        Answer answer = mQuestionerUser.answerQuestion(question, "answer");
        expectedException.expect(VotingException.class);
        mQuestionerUser.downVote(answer);
    }

    @Test
    public void whenOtherUserIsTryingToAcceptAnswerAnswerAcceptanceExceptionIsThrown()
            throws Exception {
        // Given board, answerer, and questioner
        // When questioner asks question, answerer answers and
        // answerer accepts his own answer
        // Then AnswerAcceptanceException should be thrown
        Question question = mQuestionerUser.askQuestion("question");
        Answer answer = mAnswererUser.answerQuestion(question,"answer");
        expectedException.expect(AnswerAcceptanceException.class);
        mAnswererUser.acceptAnswer(answer);
    }

    @Test
    public void questionerAcceptingAnswerToHisQuestionSetsAnswerIsAccepted()
            throws Exception {
        // Given board, answerer, and questioner
        // When questioner asks question, answerer answers and questioner
        // accepts answer
        Question question = mQuestionerUser.askQuestion("question");
        Answer answer = mAnswererUser.answerQuestion(question,"answer");
        mQuestionerUser.acceptAnswer(answer);
        // Then answer should be set to accepted
        assertTrue(answer.isAccepted());
    }

    @Test
    public void downVotingQuestionDoesNotAffectQuestionerReputation()
            throws Exception {
        // Given board, other user and questioner
        int initialQuestionerReputation = mQuestionerUser.getReputation();
        // When question is asked by questioner and then downvoted by other user
        Question question = mQuestionerUser.askQuestion("question");
        mOtherUser.downVote(question);
        // Then questioner reputation should not change
        assertEquals(initialQuestionerReputation,
                mQuestionerUser.getReputation());
    }

    @Test
    public void downVotingAnswerReducesAnswererReputationByMinusOne() {
        // Given board, answerer, and questioner
        int initialAnswererReputation = mAnswererUser.getReputation();
        // When questioner asks question, answerer answers and questioner
        // downvotes answer
        Question question = mQuestionerUser.askQuestion("question");
        Answer answer = mAnswererUser.answerQuestion(question,"answer");
        mQuestionerUser.downVote(answer);
        // Then answerer reputation should go down by 1
        assertEquals(initialAnswererReputation - 1,
                mAnswererUser.getReputation());
    }
}