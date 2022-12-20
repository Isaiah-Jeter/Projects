#lang racket
(provide make-change splittable?)

;input a total and a list of coins available
;output if it is possible to use the coins available to make the total
(define (make-change total coin-list)
  (cond [(= total 0) true]
        [(> total 0) (if(empty? coin-list)
                         false
                         (if(make-change (- total (first coin-list)) (rest coin-list)) ;use it
                             true
                             (make-change total (rest coin-list))))] ;lose it
        [else false]))


;input a list and outputs whether or not the list can be split into two lists that have the same sum
(define (splittable? values)
  (cond [(empty? values) true] ;base case
        ;if the list is one element long, return if the element equals 0 ('(0) is splittable as '() and '(0)
        [(empty? (rest values)) (if (= (first values) 0)
                                    true
                                    false)]
        ;if the list has two elements, return if they are equal
        [(empty? (rest(rest values))) (cond [(=(first values) (first(rest values))) true]
                                            [else false])]
        ;if the list has two or more elements
        [else (if(splittable? (cons (+ (first values) (first(rest values))) (rest(rest values)))) ;use it in current sum
                 true
                 (splittable? (cons (first values) (cons (+ (first(rest values)) (first(rest(rest values)))) (rest(rest(rest values)))))))])) ;lose it
         

