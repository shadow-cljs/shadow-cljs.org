
shadow-cljs provides everything you need to compile your ClojureScript code with a focus on simplicity and ease of use.

* Good configuration defaults so you don't have to sweat the details
* Supporting various targets :browser, :node-script, :npm-module, :react-native(exprimental)...
* Live Reload (CLJS + CSS)
* CLJS REPL
* Importing CommonJS & ES6 modules from npm or local JavaScript files
* Code splitting (via :modules)
* Fast builds, reliable caching, ...

To get shadow-cljs, run:

```bash
npm install -g shadow-cljs
```

![shadow-cljs demo](/entry/shadow-cljs-demo.png)

Mind that shadow-cljs needs JVM in order to compile ClojureScript.

[Try with an demo project!](https://github.com/minimal-xyz/minimal-shadow-cljs-browser)

### Configuration

Configuring a browser app with `shadow-cljs.edn`:

```clojure
{:source-paths ["src"]
 :dependencies [[reagent "0.8.0-alpha2"]]
 :builds {:app {:target :browser
                :output-dir "public/js"
                :asset-path "/js"
                :modules {:main {:entries [my.app]}}}}}
```

Supposing the namespace for you main file is `my.app`:

```bash
shadow-cljs.edn
src/
  my/
    app.cljs
public/
  js/
```

`:target :browser` specifies it will build code for browsers. You may use `:target :node-script` [for Node.js code](https://github.com/minimal-xyz/minimal-shadow-cljs-nodejs). Find more about [targets](https://shadow-cljs.github.io/docs/UsersGuide.html#_build_target).

### CLI tools

Commonly used shadow-cljs commands during development:

```bash
# compile a build once and exit
shadow-cljs compile app

# compile and watch
shadow-cljs watch app

# connect to REPL for the build (available while watch is running)
shadow-cljs cljs-repl app

# connect to standalone node repl
shadow-cljs node-repl
```

Running a release build optimized for production use.

```bash
shadow-cljs release app
```

### Import npm modules

With shadow-cljs, most npm modules for browser can be imported with modules installed locally.

```clojure
(ns app.main
  (:require ["md5" :as md5]
            ["fs" :as fs]))

(println (md5 "text"))

(fs/readFileSync "deps.den" "utf8")
```

Read more at [Guide on how to use/import npm modules/packages in ClojureScript?](https://clojureverse.org/t/guide-on-how-to-use-import-npm-modules-packages-in-clojurescript/2298)

### Hot code swapping

shadow-cljs watch file changes and re-compiles in watching mode. Code are compiled incrementally, warnings and errors are displayed after prettified.

```clojure
{:source-paths ["src"]
 :dependencies [[mvc-works/hsl "0.1.2"]]
 :builds {:browser {:target :browser
                    :output-dir "target/browser"
                    :modules {:main {:entries [app.main]}}

                    :devtools {:after-load app.main/reload!
                               :http-root "target"
                               :http-port 8080}}}}
```

### Long term caching

By setting in `:module-hash-names` field, you may tell shadow-cljs to add MD5 hash in the filenames generated. It's a trivial feature in Webpack, now it's one-liner config in ClojureScript. Meanwhile `assets.edn` file can be emitted for indexing js files in HTML.

```clojure
{:source-paths ["src"]
 :dependencies [[mvc-works/hsl "0.1.2"]]
 :builds {:browser {:target :browser
                    :output-dir "target/browser"
                    :modules {:main {:entries [app.main]}}

                    :release {:output-dir "dist/"
                              :module-hash-names 8
                              :build-options {:manifest-name "assets.edn"}}}}}
```

Afer compilation, two files will be generated in `dist/` with names:

```
=>> l dist/
assets.edn        main.9683CD2F.js
```

### Other features

There are more features you may explore in shadow-cljs:

* [Dynamic code loading](https://shadow-cljs.github.io/docs/UsersGuide.html#_loading_code_dynamically)
* [Testing](https://shadow-cljs.github.io/docs/UsersGuide.html#_testing)
* [Editor Integration](https://shadow-cljs.github.io/docs/UsersGuide.html#_editor_integration)

Read [User Guide](https://shadow-cljs.github.io/docs/UsersGuide.html) for more features.
