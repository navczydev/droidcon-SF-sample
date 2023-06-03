# droidcon-SF-sample

## What's up with Android's Back

- Sample code about how we can use ```onBackPressedDispatcher``` to customize the back behavior

- Add ```OnBackPressedCallback``` to Dialog, Activity, Fragment, and Composables(```BackHandler()```)

- Customize the UI components(ex: scaling, tranlating, etc.) using ```handleOnBackProgressed````

- Using ```Transtion API``` feom ```AndroidX``` with ```OnBackPressedCallback``` to animate the objects

- Cross-activity animations

- Custom cross-activity animations using the following new APIs introduced in **Android14**
```Kotlin

overrideActivityTransition(OVERRIDE_TRANSITION_CLOSE, R.anim.slide_in_left, R.anim.slide_out_left)

overrideActivityTransition(OVERRIDE_TRANSITION_OPEN, R.anim.slide_in_left, R.anim.slide_out_left)

```

## How to enable Gesture based navigation in Android based phone 📲

# Step1.

![Navigatio-mode-settings-1](https://github.com/navczydev/droidcon-SF-sample/assets/8358882/d3dac850-8396-4a83-8204-fcd382688fbc)

# Step2.

![Navigation-settings-screen](https://github.com/navczydev/droidcon-SF-sample/assets/8358882/12518f62-1b4a-4282-af0b-46b135b198fc)


## UI content

**MainActivity content composable preview**

![MainContent](https://github.com/navczydev/droidcon-SF-sample/assets/8358882/9d5f3c6b-fa1f-4cf5-942e-3e9181afbb91)

**BottomSheet content composable preview**

![BottoSheetContent](https://github.com/navczydev/droidcon-SF-sample/assets/8358882/7f6028bd-4be7-4385-8966-ff97b4941234)

** SideSheet conten composable perview

![SideSheetConten](https://github.com/navczydev/droidcon-SF-sample/assets/8358882/6f089f36-e3ec-46a5-8de8-b3bad881309d)
