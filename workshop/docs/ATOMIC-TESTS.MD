# Automated Atomic Tests

No, not the best tests that we can code
![Not the best](https://media.giphy.com/media/JtLrtaN4VPoKXJRKGB/giphy.gif)

An **automated atomic test (AAT)** is one that tests only a single feature or component. An AAT should form a single irreducible unit. An automated test should not do something like end-to-end automation. 

We can usually tell that a test is atomic when:

* The test will only have one assertion or two assertions at most. Because sometimes we need one assertion to make sure our state is correct
* Atomic tests have very few UI interactions and they’re only on a maximum of two screens. In rare cases, an atomic test might navigate through 3 screens (although I’d like to see this example)

## Advantages of atomic tests
1. Atomic tests fail fast
2. Atomic tests decrease flaky behavior
3. Atomic checks allow for focused testing
4. Atomic tests are short and fast (run in milliseconds)

As an aside, this concept is already well understood in unit and integration tests, but UI tests continue to lag behind.

[Read more](https://ultimateqa.com/automated-atomic-tests/)

## Let's make our tests atomic

1. 🏋️‍♀️Go to E2ETests.java and follow instructions in `userCanCheckoutAtomic()` to create an atomic test that validates checkout logic
2. Don't forget to run the test until it passes

---

## ❓Questions or concerns about this process

---
