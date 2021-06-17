# Flipkart JEDI 4 - Group 4 Project 

This repo contains the Source code and UML diagrams for the WIP course management system project being constructed as a part of the JEDI Program for Flipkart Summer SWE Interns.

---
## Contributing Guidelines

Please work on your own forks of this main repo. Once done submit a pull-request and wait for it to be merged. After your work has been succesfully reviewed and merged, you can update your fork to the latest commit on the upstream repository by adding a new remote and pulling the changes. 

Consider you're working on a feature called feature-name. Then follow the below mentioned workflow.

 1. Make sure your local copy is on master branch and is upto date. `git checkout master && git pull`
 2. Create a new branch. `git checkout -b feature-name`
 3. Work on this branch and make changes that you wish to. Try to not making excessive commits as that will increase the size of the repo. Git stores all changes so deleting a file and recomitting will not reduce size.
 4. When done with final commit. Push to remote using `git push origin feature-name`
 5. Go to github and create a pull request. Ideally wait for someone else to merge your pull request. Always go with the "Create a merge commit" option while merging.
 6. If any changes are requested, do the changes on your local branch, commit and push again. No need to close the pull-request and create a new one.
 7. Once the PR is merged delete your local branch using `git branch -d feature-name` and delete remote branch using `git push origin :feature-name`
