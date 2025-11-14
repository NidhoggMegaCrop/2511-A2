# Assignment-ii <!-- omit in toc -->

## 0. Due: Week 10 Wednesday, 3pm [Sydney Local Time](https://www.timeanddate.com/worldclock/australia/sydney) (19th November 2025) <!-- omit in toc -->

- [1. Part 1 \[65 Marks\]](#1-part-1-65-marks)
- [2. Part 2 \[35 Marks\]](#2-part-2-35-marks)
- [3. Other Information](#3-other-information)
  - [3.1. Late Penalties](#31-late-penalties)
  - [3.2. Extensions](#32-extensions)
  - [3.3. Plagiarism](#33-plagiarism)
- [4. Copyright Notice](#4-copyright-notice)
- [5. Credits](#5-credits)

Key notes:

- There are **two** parts to this assignment.
- There are no "Short Extensions" from Special Consideration for this assignment.
- 5% per day late penalty calculated per-hour to the next hour. Maximum of 5 days late penalty. No submissions will be accepted greater than 5 days after the assignment due date.
- ELS/ELP and Special Consideration will have maximum 7 days of an extension which includes the maximum 5 days late penalty.
- [Submission Part 1]: We will take the contents of your `main` branch your final submission. See [this](#3-other-information).
- [Submission Part 2]: This must be submitted on [Moodle](https://moodle.telt.unsw.edu.au/mod/turnitintooltwo/view.php?id=8162379). **Your submission must be a single PDF containing all diagrams!**
- Ensure your Part 1 submission compiles with our dryrun [here](./part-1/Part1_Specification.md#63-dryruns).
- Please visit the [course website](https://cgi.cse.unsw.edu.au/~cs2511/25T3/els-spec-cons) for Special Consideration or Equitable Learning Plans.

Examples for late submissions:

- If you submit 5 days late (original due date + 5 days), your mark will be reduced by 20%.
- If you submit 5 days, 3 hours late (original due date + 5 days, 3 hours), any work completed after the 5 day mark will not be marked or accepted.
- If you have a 7 day extension, the latest submission accepted is original due date + 7 days.
- If you have a 7 day extension and ELP/ELS for 7 days, the latest submission accepted is original due date + 7 days + 7 days.

# 1. Part 1 [65 Marks]

In this assignment, you are provided an existing developed system. You will first need to analyse and refactor the code, then adapt the system to an evolution in requirements.

The aims of this part can be broken down into five major themes:

1. **Acclimatising to an existing system.** For many of you this will be the largest codebase you have worked on to date - which can be very daunting! Being able to work with a system that you haven’t built from scratch and don’t fully understand is a vital skill, since software is never developed in isolation.
2. **Refactoring Techniques.** Like when you go camping, you always want to leave the code in a better state than you found it in. We’ve intentionally put in a series of design flaws, with accompanying design smells for you to discover and refactor.
3. **Design Patterns.** Being able to see patterns in existing code, and to use patterns to improve code quality is an essential skill in a Software Engineer. You’ll have the chance to do this and apply the theoretical ideas discussed in lectures.
4. **Evolution of Requirements.** Software is never static - it always evolves and grows as do its requirements. You’ll need to build on the existing system to accommodate for these changes, in doing so undergo an iterative design and development process.
5. **Dealing with The Unknown.** There are many unknowns in this assignment that you will encounter. You will need to explore and investigate, clarify and ask for help where needed and approach these unknowns with grace in order to succeed.

This spec is split into **two** sections.

1. [Product Specification - MVP](./part-1/MVP.md) contains the requirements the existing MVP we've provided was built to.
2. [Part 1 Specification](./part-1/Part1_Specification.md) contains your tasks you'll need to complete for this assignment.

We have also included some extra documentation which may be useful to you.

- [Setting up](./part-1/Setup.md)
- [Customisations](./part-1/Customisations.md)
- [Dungeon Map Creation Tool](https://cs2511-dungeonmania-map-generator.vercel.app) (for assistance only, may be incomplete)

You can also check out [this video](https://www.loom.com/share/6bdd612614014a96b801de72f8b5a2c1?sid=5c47d8fe-eada-4346-87e0-5b7d36ae9c3f) to help you get started on part 1 of the assignment.

# 2. Part 2 [35 Marks]

Part 2 focuses on content introduced from Week 7 onward. Two tutorials and labs will explore key topics related to this part. In this part, you will model the high-level architecture of a system,
focusing on both structural and behavioural aspects. The case study is inspired by realistic, industry-relevant requirements to provide a practical design experience. These models will be developed
using the C4 architectural notation and behavioural modelling.

- [Part 2 Specification](./part-2/Part2_Specification.md)

You can also check out [this video](https://www.loom.com/share/b3e6b6d8fb754bcba664e4e98610c3d8?sid=ff5a1829-615b-42b3-9449-9662320cd1c2) to help you get started on part 2 of the assignment.

# 3. Other Information

## 3.1. Late Penalties

The late penalty for the submission is the standard UNSW late penalty of a 5% per day reduction of the on-time assignment mark. This is calculated per-hour to the next hour. For example, if the assignment would receive an on-time mark of 70% and was submitted 3 days late the actual mark would be 55%. An assignment submitted 1 hour late would only have their mark reduced by (5/24)% ≈ 0.21%.

**No submissions will be accepted greater than 5 days after the assignment due date.**

Please visit the [course website](https://cgi.cse.unsw.edu.au/~cs2511/25T3/els-spec-cons) for more information.

Examples for late submissions:

- If you submit 5 days late (original due date + 5 days), your mark will be reduced by 20%.
- If you submit 5 days, 3 hours late (original due date + 5 days, 3 hours), any work completed after the 5 day mark will not be marked or accepted.
- If you have a 7 day extension, the latest submission accepted is original due date + 7 days.
- If you have a 7 day extension and ELP/ELS for 7 days, the latest submission accepted is original due date + 7 days + 7 days.

## 3.2. Extensions

Extensions are only granted in extenuating circumstances and must be approved through either Special Consideration, which needs to be submitted prior to the assignment deadline, or pre-arranged through an Equitable Learning Plan with Equitable Learning Services and the Course Authority. In all cases please email [cs2511@cse.unsw.edu.au](mailto:cs2511@cse.unsw.edu.au).

There will be no "Short Extensions" from Special Consideration for this assignment.

Please visit the [course website](https://cgi.cse.unsw.edu.au/~cs2511/25T3/els-spec-cons) for more information.

## 3.3. Plagiarism

Your program must be entirely your work. Plagiarism detection software will be used to compare all submissions pairwise (including submissions for similar assignments in previous years, if applicable) and serious penalties will be applied, including an entry on UNSW's plagiarism register. Relevant scholarship authorities will be informed if students holding scholarships are involved in an incident of plagiarism or other misconduct.

You are also not allowed to submit code obtained with the help of ChatGPT, GitHub Copilot, Gemini or similar automatic tools.

- Do not copy ideas or code from others.
- Do not use a publicly accessible repository or allow anyone other than yourself to see your code, except for the teaching staff of COMP2511.
- Code generated by ChatGPT, GitHub Copilot, Gemini and similar tools will be treated as plagiarism.

The course reserves the right to ask you to explain your code or design choices to a member of staff as part of your submission, or complete a similar assessment.

Please refer to the on-line resources to help you understand what plagiarism is and how it is dealt with at UNSW:

- [Academic Integrity and Plagiarism](https://www.student.unsw.edu.au/plagiarism/integrity)
- [UNSW Plagiarism Policy](https://www.unsw.edu.au/content/dam/pdfs/governance/policy/2022-01-policies/plagiarismpolicy.pdf)

# 4. Copyright Notice

Reproducing, publishing, posting, distributing or translating this assignment is an infringement of copyright and will be referred to UNSW Student Conduct and Integrity for action.

# 5. Credits

If no specific license is specified it's public domain permissible (i.e. usable in commercial/non-commercial products) but no explicit license was found.

- Frontend + monolith built by cs2511: Braedon Wooding, Nick Patrikeos, George Litsas, Noa Challis, Chloe Cheong, Sienna Archer, Tina Ji, Webster Zhang, Adi Kishore, Amanda Lu
- The one ring: Created by Jordan Irwin (AntumDeluge)
- Mercenary: Animated Ranger by Calciumtrice, usable under Creative Commons Attribution 3.0 license.
- Portals: Portals made by RodHakGames - RHG
- Boulder: This work, made by Viktor Hahn (Viktor.Hahn@web.de), is licensed under the Creative Commons Attribution 4.0 International License. Creative Commons — Attribution 4.0 International — CC BY 4.0
- Alagard Font: Made by Pix3M, usable under Creative Commons Attribution 3.0 license.
- Armor + Shield: Made by Zeno
- Tileset + Some Random Entities: Made by egordorichev, these assets are public domain and free to use on whatever you want, personal or commercial (aka CC0 license).
- Coin/Treasure: By La Red Games
- Zombie Toast: By LHTeam (LazyHamsters )
- Toaster: By Reakain; LICENCE: This asset pack can be used in both free and commercial projects. You can modify it to suit your own needs. Credit is not necessary, but very appreciated. You may not redistribute it or resell it.
- Spider: By Elthen
- Assignment authored by Robert Clifton-Everest, Braedon Wooding, Ashesh Mahidadia, Fethi Rabhi, Nick Patrikeos and Amanda Lu.
