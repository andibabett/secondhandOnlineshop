package org.fasttrackit.secondhandOnlineshop.service;

import org.fasttrackit.secondhandOnlineshop.domain.Review;
import org.fasttrackit.secondhandOnlineshop.persistance.ReviewRepository;
import org.fasttrackit.secondhandOnlineshop.transfer.review.AddReviewToProductRequest;
import org.fasttrackit.secondhandOnlineshop.transfer.review.SaveReviewRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private static Logger LOGGER = LoggerFactory.getLogger(ReviewService.class);

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review createReview(SaveReviewRequest request) {
      LOGGER.info("Creating review{}",request);

      Review review = new Review();
      review.setContent(request.getContent());
      review.setId(request.getProductId());

       reviewRepository.save(review);

        return review;
    }

    public void addReviewToProduct(AddReviewToProductRequest request) {
        LOGGER.info("Adding review to product: {}",request);
     reviewRepository.findById(request.getProductId());
    }

    public Review getReview(long id) {
        LOGGER.info("Retrieving review {}",id);
        return reviewRepository.findById(id).orElseThrow(()->new RuntimeException("Review" + id +"not found."));
    }

    public void deleteReview(long id) {
        LOGGER.info("Deleting review {}",id);
        reviewRepository.deleteById(id);
    }

    public Review updateReview(long id , SaveReviewRequest request) {
        LOGGER.info("Updating review {}", request);
        Review review = getReview(id);
        BeanUtils.copyProperties(request,review);
        return reviewRepository.save(review);

    }
}
