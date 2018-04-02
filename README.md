
shadow-cljs.org
----

> Home page for [shadow-cljs](github.com/thheller/shadow-cljs)

http://shadow-cljs.org

### Usage

This project uses yarn to manage tasks and dependencies:

```yarn
yarn
```

Develop:

```bash
yarn watch # listens on 7000

# after sever started, generate HTML fisrt
yarn shadow-cljs clj-run build.main/html
```

Build:

```bash
yarn shadow-cljs clj-run build.main/build-local
yarn http-server dist
```

### Workflow

Workflow https://github.com/mvc-works/calcit-workflow

### License

MIT
