package org.fasttrackit.secondhandOnlineshop.web;

import org.fasttrackit.secondhandOnlineshop.domain.Review;
import org.fasttrackit.secondhandOnlineshop.service.ReviewService;
import org.fasttrackit.secondhandOnlineshop.transfer.review.AddReviewToProductRequest;
import org.fasttrackit.secondhandOnlineshop.transfer.review.SaveReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/review")
public class ReviewController {


private final ReviewService reviewService;
    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @PostMapping
    public ResponseEntity<Review>createReview(@RequestBody @Valid SaveReviewRequest request) {
        Review review = reviewService.createReview(request);
        return new ResponseEntity<>(review, HttpStatus.CREATED);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Review>getReview(@PathVariable("id") long id ) {
        Review review = reviewService.getReview(id);
        return new ResponseEntity<>(review, HttpStatus.OK);

    }
    @PutMapping
    public ResponseEntity addReviewToProduct( @RequestBody  @Valid AddReviewToProductRequest request) {
       reviewService.addReviewToProduct(request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
    @PutMapping("/{id}/{itemId}")
    public ResponseEntity updateReview(@PathVariable("id") long id, @RequestBody @Valid SaveReviewRequest request) {
        reviewService.updateReview(id, request);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReview(@PathVariable("id") long id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
