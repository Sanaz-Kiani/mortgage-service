package com.ing.mortgage.validator.specification;

/**
 * A composite specification that represents the logical AND operation between two specifications.
 *
 * @param <T> The type of the entity being evaluated by the specification.
 */
public class AndSpecification<T> implements Specification<T> {
    private final Specification<T> left;
    private final Specification<T> right;

    /**
     * Constructs a new AndSpecification with the specified left and right specifications.
     *
     * @param left  The left specification.
     * @param right The right specification.
     */
    public AndSpecification(Specification<T> left, Specification<T> right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Checks if the given entity satisfies both the left and right specifications.
     *
     * @param entity The entity to be evaluated.
     * @return {@code true} if the entity satisfies both specifications, {@code false} otherwise.
     */
    @Override
    public boolean isSatisfiedBy(T entity) {
        return left.isSatisfiedBy(entity) && right.isSatisfiedBy(entity);
    }
}