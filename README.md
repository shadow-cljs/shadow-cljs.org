
shadow-cljs.org
----

> Home page for [shadow-cljs](github.com/thheller/shadow-cljs)

http://shadow-cljs.org

Content mainly in markdown file [markdown/introduction.md](markdown/introduction.md). Fork, edit, and send PR if you'd like to help.

### Usage

Develop:

```bash
yarn
yarn watch # HTTP server listening on 7000

# generate HTML from another terminal after watch server started
yarn page
# add soft link for the CSS file
(cd target && ln -s ../entry/)
```

Build:

```bash
yarn build-local
yarn http-server dist
```

### Workflow

Workflow https://github.com/mvc-works/calcit-workflow

### License

MIT
