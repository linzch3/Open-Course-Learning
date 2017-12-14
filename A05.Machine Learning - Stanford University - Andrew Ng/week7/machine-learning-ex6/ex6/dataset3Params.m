function [C, sigma] = dataset3Params(X, y, Xval, yval)
%DATASET3PARAMS returns your choice of C and sigma for Part 3 of the exercise
%where you select the optimal (C, sigma) learning parameters to use for SVM
%with RBF kernel
%   [C, sigma] = DATASET3PARAMS(X, y, Xval, yval) returns your choice of C and 
%   sigma. You should complete this function to return the optimal C and 
%   sigma based on a cross-validation set.
%

% ====================== YOUR CODE HERE ======================
% Instructions: Fill in this function to return the optimal C and sigma
%               learning parameters found using the cross validation set.
%               You can use svmPredict to predict the labels on the cross
%               validation set. For example, 
%                   predictions = svmPredict(model, Xval);
%               will return the predictions on the cross validation set.
%
%  Note: You can compute the prediction error using 
%        mean(double(predictions ~= yval))
% You need to return the following variables correctly.
% --------------
C = 0;
sigma = 0;
% --------------
% here is the parameter I run for the optimal C and sigma
% C_wait2test = [0.0001, 0.0003, 0.001, 0.003, 0.01, 0.03, 0.1,0.3,1,3,10,30,100,300];
% sigma_wait2test = C_wait2test;
C_wait2test = [0.3];
sigma_wait2test = [0.1];

min_error_rate = 100;
for c = C_wait2test
    for s = sigma_wait2test
       model = svmTrain(X, y, c, @(x1, x2) gaussianKernel(x1, x2, s));
       predictions = svmPredict(model, Xval);
       error_rate = mean(double(predictions ~= yval));
       
       if error_rate < min_error_rate
            min_error_rate = error_rate;
            C = c;
            sigma = s;
       end
    end
end
 
% =========================================================================
% debug code 
% ***********************************************
% clc  
% model= svmTrain(X, y, C, @(x1, x2) gaussianKernel(x1, x2, sigma));
% visualizeBoundary(X, y, model);
% C
% sigma
% min_error_rate
% ***********************************************
end
