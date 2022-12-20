#lang racket

(require "tree-library.rkt")
(provide treemap insert sort-values)

;function treemap
;input, a tree and a function
;output, a tree where the function has been applied to all elements of the tree
;like list map, but fortree
(define (treemap f tree)
  (cond [(empty-tree? tree) empty-tree] ;base case
        [(and (empty-tree? (left tree)) (empty-tree? (right tree))) (make-leaf (f(root tree)))] ;if root is leaf, no subtrees
        [(empty-tree? (left tree)) (make-tree (f(root tree)) empty-tree (treemap f (right tree)))] ;if no left children
        [(empty-tree? (right tree)) (make-tree (f(root tree)) (treemap f (left tree)) empty-tree)] ;if no right children
        [else (make-tree (f(root tree)) (treemap f (left tree)) (treemap f (right tree)))])) ;if both left and right children

;function insert
;input a value and a binary search tree
;output a binary search tree where the value has been added in the correct location
;if the value is less than the node it goes to the left, if greater than the node it goes to the right
(define (insert value bst)
  (cond [(empty-tree? bst) (make-leaf value)] ;if adding to empty tree
        ;if the value goes to the left of the tree
        [(< value (root bst)) (if(empty-tree? (left bst)) ;if there is no node to the left                         
                                 (make-tree (root bst) (make-leaf value) (right bst)) ;if there is a node to the left, add it in the correct location
                                 (make-tree (root bst) (insert value (left bst)) (right bst)))]
        [(> value (root bst)) (if(empty-tree? (right bst))
                                 (make-tree (root bst) (left bst) (make-leaf value))
                                 (make-tree (root bst) (left bst) (insert value (right bst))))]))
;input a list and a tree (empty tree to create new list)
;output a tree where all values from the list have been added
(define (make list tree)
  (if(empty? list)
     tree
     (make (rest list) (insert (first list) tree))))
;input a list of values, output a list where the values have been sorted
;adds the values to a BST and traverses in order to sort
(define (sort-values values)
  (traverse-inorder (make values empty-tree)))
  
  
        
        
        
   
; Write your functions in this file. Remember to document them!