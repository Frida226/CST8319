# CST 8319 - Flower Order System


## Branching Model
master - The main branch representing the stable, production-ready code. This branch should always be in a working condition. All feature branches should branch off from the master. After a feature is completed and tested, it should be merged back into the master branch.
Feature Branches - Working branches that branch off from master. Each team member will create branches off the master, work on their feature, and merge the feature branch back to master. Before merging a feature branch into master, merge master into the feature branch first, test to exclude any merging/integration issues. If stable, merge the feature branch into the master branch.
## Feature Development Workflow
- Each collaborator works on a separate feature branch for their assigned task.
- Regularly pull changes from master to keep feature branches up to date.
- Once a feature is complete, the collaborator submits a pull request (PR) from their feature branch to master.
- PR should include code changes, a summary of the changes, and any related issues.
- Ensure that no one directly pushes changes to master.
## Code Review
- Before merging a feature branch into master, require at least one code review from another team member.
- Reviewers should ensure code quality, adherence to coding standards, and correctness.
- Address feedback and make necessary changes before merging.
## Prepare workspace
When you clone the project, run it on the server and make sure you can see login.jsp in the browser, i.e., the server is running and requests are forwarded to the controller. This is to ensure everybody is on the same page and local environments are set up properly.
## Environment Setup
- Tomcat Version: v9.0
- MySQL Connector: mysql-connection 8.0.13 jar
