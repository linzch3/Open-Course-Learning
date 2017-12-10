function [J, grad] = costFunctionReg(theta, X, y, lambda)
%COSTFUNCTIONREG Compute cost and gradient for logistic regression with regularization
%   J = COSTFUNCTIONREG(theta, X, y, lambda) computes the cost of using
%   theta as the parameter for regularized logistic regression and the
%   gradient of the cost w.r.t. to the parameters. 

% Initialize some useful values
m = length(y); % number of training examples

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost of a particular choice of theta.
%               You should set J to the cost.
%               Compute the partial derivatives and set grad to the partial
%               derivatives of the cost w.r.t. each parameter in theta
% Hint: You should not regularize theta(1)
h_theta_x =sigmoid(X*theta);
J = -1/m*(y'*log(h_theta_x)+(1-y')*log(1-h_theta_x))+ lambda/(2*m)*sum(theta(2:end).^2);
grad = 1/m*X'*(sigmoid(X*theta)-y);
grad(2:end) = grad(2:end) + lambda/m*theta(2:end);
% =============================================================

end
