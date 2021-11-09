# automation-best-practices-java
Automation best practices workshop with Java

![Testing for good](../graphics/testing-good.jpeg)

[#testing4good](https://twitter.com/hashtag/Testing4Good)

In this automation best practices workshop you will learn the latest and greatest tools and techniques to drastically improve your testing!

We will focus on a holistic approach of testing front-end and back-end, web and APIs, functional testing, component testing, and many other things in between 😁

This workshop serves 2 purposes:

1. For me to give back to the testing world and help us all upskill 🚀
2. For us all to help a greater cause than ourselves 🌍

### [ABOUT CHARITY]()



👇👇👇

[Please donate whatever you feel appropriate.](https://www.gofundme.com/f/testing-for-good-codeorg) 100% of the donations go to the cause.

## 🧠You will learn

* Create a framework for doing comprehensive web testing
* Create functional browser tests using Selenium
* Code visual e2e tests using Screener
* Run in massive parallel (100s of tests in < 5 min)
* Run in a CI pipeline

## 🔧Technologies you will use

1. 
5. Sauce Labs
6. Github Actions
7. 

## Table Of Contents

* Introduction to workshop
* [Local environment setup](#Local-environment-setup)
* [Gitpod environment setup (alternative approach to local)](#gitpod-setup)
* 

## Requirements

**This is NOT a beginners course and you will not learn Java testing fundamentals here. However, you will learn a number of amazing skills, techniques, and tools to help you test web applications**

* At least 1 year of Java programming
* Deep understanding of Selenium WebDriver
* Deep understanding of OOP
* Java 8 installed
* Java IDE installed
* Git installed
* Maven installed

## Key

💡 this is a tip

🏋️‍♀️ this is an exercise for you to do

❓ this is a question for us to think and talk about. Try not to scroll beyond this question before we discuss

## Your Instructor: Nikolay Advolodkin

<img src="./../graphics/me-and-mia.jpg" alt="me" width="150"/>

- 🔭 I’m the founder of [Ultimate QA](https://ultimateqa.com/)
- 🏢 I’m a Sr Solutions Architect at Sauce Labs
- 🌱 I’m currently working on [Sauce Bindings](https://github.com/saucelabs/sauce_bindings)
- 💬 Ask me about environmentalism, veganism, test automation, and fitness
- 😄 Pronouns: he/him
- ⚡ Fun fact: I'm a vegan that's super pasionate about saving the planet, saving animals, and helping underpriveleged communities
- 📫 Follow me for testing and dev training
  - [Java Testing Newsletter]()
  - [Youtube](https://youtube.com/ultimateqa)
  - [LinkedIn](https://www.linkedin.com/in/nikolayadvolodkin/)
  - [Twitter](https://twitter.com/Nikolay_A00)

## Your TA: Eyal

<img src="./../graphics/josh-grant.jpeg" alt="josh" width="150"/>



## Setup

### Sign up for accounts

1. Free [Sauce account](https://saucelabs.com/sign-up)
2. Request [Demo Secreener account](https://saucelabs.com/demo-request-vt). **!You must request this at least a week before the workshop as it's a manual process to add users.** 

### Local environment setup

Fork then clone the repo

1. Sign up for a free [Github account](https://github.com/)
2. Fork this respository
 * Make sure you are logged into Github
 * click the Fork in the upper right of the Github.
3. Clone your fork of the repository to your machine. Must have [Git installed](https://git-scm.com/downloads)

```bash
git clone URL_OF_YOUR_FORK
```

Navigate to the directory of where you cloned your repo

`cd YOUR_FORK_DIR/automation-best-practices/workshop`

Run sanity tests

```java
mvn test -Dtest=SanityTest -X
```

<br/>
  <details>
    <summary>
      <strong>Click here</strong> to see an example console output.
    </summary>

        
        Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 54.305 sec

        Results :

        Tests run: 2, Failures: 0, Errors: 0, Skipped: 0

        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------
        [INFO] Total time:  56.063 s
        [INFO] Finished at: 2021-11-03T16:03:20-04:00
        [INFO] ------------------------------------------------------------------------
        
  </details>

</br>

> If you weren't successful at setting up you local env, then use the [Gitpod approach](#gitpod-setup)👇


### Gitpod setup

Open forked repo in Gitpod.io

1. Sign up for a free [Github account](https://github.com/)
2. Fork this repository
    * Make sure you are logged into Github
    * Click the fork in the upper right of Github
    * Select your username as the location to fork the repo
3. In the browser address bar, prepend the Github url (`https://github.com/USERNAME/automation-best-practices-java`) with `https://gitpod.io/#`
    * The resulting url should look as follows:
   
      > https://gitpod.io/#https://github.com/USERNAME/automation-best-practices-java
   
4. Once the Gitpod.io URL is loaded, you will need to sign in with the Github account you created earlier 
5. Once the development environment is loaded, you should see 'Ready to test!' in the Terminal window in the lower portion of the window, run the following commands in that Terminal to set your `SAUCE_USERNAME`, `SAUCE_ACCESS_KEY`, and `SCREENER_API_KEY`:

> :information_source: You can get your Sauce Labs Username and Access Key by going to the [Sauce Labs user settings page](https://app.saucelabs.com/user-settings)
  </br>
> :information_source: You can get your Screener API Key by going to the [API key](https://screener.io/v2/account/api-key) page in your Screener settings

```bash
eval $(gp env -e SAUCE_USERNAME=<sauce_username>)
eval $(gp env -e SAUCE_ACCESS_KEY=<sauce_access_key>)
eval $(gp env -e SCREENER_API_KEY=<screener_api_key>)
```

> Replace <sauce_username>, <sauce_access_key>, and <screener_api_key> with your credentials

Once you have run those 3 commands, you can run the following commands to test your environment variables:

```bash
echo $SAUCE_USERNAME
echo $SAUCE_ACCESS_KEY
echo $SCREENER_API_KEY
```

Run sanity tests

```bash
mvn test -Dtest=SanityTest -X
```

<br/>
  <details>
    <summary>
      <strong>Click here</strong> to see an example console output.
    </summary>


        Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 54.305 sec

        Results :

        Tests run: 2, Failures: 0, Errors: 0, Skipped: 0

        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------
        [INFO] Total time:  56.063 s
        [INFO] Finished at: 2021-11-03T16:03:20-04:00
        [INFO] ------------------------------------------------------------------------

  </details>

<br/>

### Add static code analysis

* Follow [Codacy instructions to setup static code analysis for your first repo](https://docs.codacy.com/getting-started/codacy-quickstart/)
* Adding and analyzing takes a bit
* [Configure code patterns for the repo](https://docs.codacy.com/repositories-configure/configuring-code-patterns/#pattern-filters)

### ✅👏Environment setup is complete

## Stay to the end and win a prize!

Stay to the end and 2 lucky people can win this snazzy Back Pack


