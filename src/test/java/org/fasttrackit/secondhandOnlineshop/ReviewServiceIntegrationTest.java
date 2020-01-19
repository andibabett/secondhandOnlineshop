package org.fasttrackit.secondhandOnlineshop;

import org.fasttrackit.secondhandOnlineshop.domain.Review;
import org.fasttrackit.secondhandOnlineshop.exception.ResourceNotFoundException;
import org.fasttrackit.secondhandOnlineshop.service.ReviewService;
import org.fasttrackit.secondhandOnlineshop.steps.ReviewSteps;
import org.fasttrackit.secondhandOnlineshop.transfer.review.SaveReviewRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewServiceIntegrationTest {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewSteps reviewSteps;

    @Test
    public void testCreateReview_whenValidRequest_thenCreateReview_() {

        reviewSteps.createReview();
    }

    @Test
    public void testGetReview_whenExistingReview_thenReturnReview() {
        Review createdReview = reviewSteps.createReview();

        Review review = reviewService.getReview(createdReview.getId());

        assertThat(review, notNullValue());
        assertThat(review.getId(), is(createdReview.getId()));
        assertThat(review.getContent(), is(createdReview.getContent()));
    }

    @Test
    public void testUpdateReviewt_whenValidRequest_thenReturnUpdatedReview() {
        Review createdReview = reviewSteps.createReview();

        SaveReviewRequest request = new SaveReviewRequest();
        request.setContent(createdReview.getId() + "updated");
        request.setContent(createdReview.getContent() + "updated");

        Review updatedReview =
                reviewService.updateReview(createdReview.getId(), request);

        assertThat(updatedReview, notNullValue());
        assertThat(updatedReview.getId(), is(createdReview.getId()));
        assertThat(updatedReview.getContent(), is(request.getContent()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteReview_whenExistingReview_thenReviewIsDeleted() {
        Review review = reviewSteps.createReview();

        reviewService.deleteReview(review.getId());

        reviewService.getReview(review.getId());
    }



}
