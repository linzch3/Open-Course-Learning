function [J, grad] = linearRegCostFunction(X, y, theta, lambda)
%LINEARREGCOSTFUNCTION Compute cost and gradient for regularized linear 
%regression with multiple variables
%   [J, grad] = LINEARREGCOSTFUNCTION(X, y, theta, lambda) computes the 
%   cost of using theta as the parameter for linear regression to fit the 
%   data points in X and y. Returns the cost in J and the gradient in grad

% Initialize some useful values
m = length(y); % number of training examples

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost and gradient of regularized linear 
%               regression for a particular choice of theta.
%
%               You should set J to the cost and grad to the gradient.
%
% You need to return the following variables correctly 
% ---------------------------------
% J = 0;
% grad = zeros(size(theta));
% ---------------------------------
%Note that you should not regularize the theta0 term.
error = X*theta - y;
J = 1/(2*m)*(sum(error.^2)+lambda*sum(theta(2:end).^2));  
grad = 1/m*(X'*error);
grad(2:end) = grad(2:end) + lambda/m*theta(2:end);
% =========================================================================
grad = grad(:);
end
