# Define the provider and required plugins
terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "3.50.0"
    }
  }
}

provider "aws" {
  region = "us-east-1"  # Update with your desired AWS region
}

# Create an AWS Lambda function
resource "aws_lambda_function" "example_lambda" {
  function_name    = "example-lambda"
  role             = aws_iam_role.lambda_role.arn
  handler          = "com.example.MyLambdaHandler::handleRequest"  # Update with your handler class and method
  runtime          = "java11"
  memory_size      = 512
  timeout          = 30
  filename         = "./target/my-lambda-function.jar"  # Update with the path to your built JAR file
  source_code_hash = filebase64sha256("./target/my-lambda-function.jar")  # Update with the path to your built JAR file

  environment {
    variables = {
      SPRING_PROFILES_ACTIVE = "production"
      OTHER_ENV_VARIABLE     = "value"
    }
  }
}

# Create an IAM role for the Lambda function
resource "aws_iam_role" "lambda_role" {
  name = "example-lambda-role"

  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Sid": "",
      "Effect": "Allow",
      "Principal": {
        "Service": "lambda.amazonaws.com"
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
EOF
}

# Attach policies to the Lambda role
resource "aws_iam_role_policy_attachment" "lambda_policy_attachment" {
  role       = aws_iam_role.lambda_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole"
}
