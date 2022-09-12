### `Git` commands for Group Work Assignment

1. Open a __folder__ in `terminal`

- Instructions: open the __folder__ with the default system's __file explorer__, right click and select '__Open in Terminal__'.

2. Navigate to the `src` folder (__source code__)

```sh
cd src
```

3. Switch between `branches`; possible branches: `michal`, `joel`, `ionel`

```sh
git checkout $NAME_OF_BRANCH
```

__Be careful__: always check for the current `branch` before _commiting_ and/or _pushing_ any `source` to the remote repository.

\*Check for current branch:
```sh
git branch
```

4. Add the __changes__ (here we add all __latest__ changes)

```sh
git add .
```

5. Create a __commit__

```sh
git commit -m "This is just a message for the others..."
```

6. __Push__ changes

```sh
git push origin $CURRENT_BRANCH_NAME
```

\*In order to __fully__ update the source, steps 4, 5, 6 must be done.
