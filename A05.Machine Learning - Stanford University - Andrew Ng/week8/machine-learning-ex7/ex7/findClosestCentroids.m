function idx = findClosestCentroids(X, centroids)
%FINDCLOSESTCENTROIDS computes the centroid memberships for every example
%   idx = FINDCLOSESTCENTROIDS (X, centroids) returns the closest centroids
%   in idx for a dataset X where each row is a single example. idx = m x 1 
%   vector of centroid assignments (i.e. each entry in range [1..K])
%

% ====================== YOUR CODE HERE ======================
% Instructions: Go over every example, find its closest centroid, and store
%               the index inside idx at the appropriate location.
%               Concretely, idx(i) should contain the index of the centroid
%               closest to example i. Hence, it should be a value in the 
%               range 1..K
%
% Note: You can use a for-loop over the examples to compute this.
%
% You need to return the following variables correctly.
% ----------------------
idx = zeros(size(X,1), 1);
% ----------------------
% dimension: X: m*n centroids:k*n
for i = 1:size(X, 1)
    %broadcast computation(using this with high focus, because X(i,:) and X(i) will run with error in the follwing code)
    distance2Kcentroids = sqrt(sum((X(i,:)-centroids).^2, 2));% k*1
    [~, idx(i)] = min(distance2Kcentroids);
end
% =============================================================
end

