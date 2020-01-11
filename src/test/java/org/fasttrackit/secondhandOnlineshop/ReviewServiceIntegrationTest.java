package org.fasttrackit.secondhandOnlineshop;

import org.fasttrackit.secondhandOnlineshop.service.ReviewService;
import org.fasttrackit.secondhandOnlineshop.steps.ReviewSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewServiceIntegrationTest {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewSteps reviewSteps;

    @Test
    public void testCreateReview_whenValidRequest_thenCreateReview_(){

    reviewSteps.createReview();


    }




}
