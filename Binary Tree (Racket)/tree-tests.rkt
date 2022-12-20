#lang racket

(require rackunit)
(require "tree-library.rkt")
(require "tree-functions.rkt")

; Note: you can define trees to use in your tests, like so:
(define tree1 (make-tree 1 (make-leaf 2) (make-leaf 3)))
(define tree2 (make-tree 5 (make-tree 7 (make-leaf 1) (make-leaf 3)) (make-tree 8 (make-leaf 9) empty-tree)))
(define tree3 (make-tree 100 empty-tree (make-leaf -1)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;  treemap tests
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(define tree4 (make-tree 3 (make-leaf 6) (make-leaf 9)))
(define tree5 (make-tree 15 (make-tree 21 (make-leaf 3) (make-leaf 9)) (make-tree 24 (make-leaf 27) empty-tree)))
(define tree6 (make-tree 300 empty-tree (make-leaf -3)))

(define (times3 n)
  (* n 3))

(check-equal? (treemap times3 tree1) tree4)
(check-equal? (treemap times3 tree2) tree5)
(check-equal? (treemap times3 tree3) tree6)
(check-equal? (treemap times3 empty-tree) empty-tree)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;  insert tests
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define bst1 (make-tree 7 (make-tree 5 (make-leaf 3) empty-tree) (make-tree 9 (make-leaf 8) (make-leaf 12))))

(define bst2 (make-tree 7 (make-tree 5 (make-leaf 3) (make-leaf 6)) (make-tree 9 (make-leaf 8) (make-leaf 12)))) ;the result of inserting 6 to bst1
(define bst3 (make-tree 7 (make-tree 5 (make-leaf 3) empty-tree) (make-tree 9 (make-leaf 8) (make-tree 12 empty-tree (make-leaf 100))))) ;the result of inserting 100 to bst1
(define bst4 (make-tree 19 empty-tree empty-tree)) ;the result of inserting 19 to an empty tree

(check-equal? (insert 6 bst1) bst2)
(check-equal? (insert 100 bst1) bst3)
(check-equal? (insert 19 empty-tree) bst4)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;  sort-values tests
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(check-equal? (sort-values '(3 0 2 1)) '(0 1 2 3))
(check-equal? (sort-values '(10 9 8 7 6 5 4 3 2 1)) '(1 2 3 4 5 6 7 8 9 10))
(check-equal? (sort-values '(1 5 10 11)) '(1 5 10 11))


