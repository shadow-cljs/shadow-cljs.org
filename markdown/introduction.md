
shadow-cljs provides everything you need to compile your ClojureScript code with a focus on simplicity and ease of use.

* Good configuration defaults so you don't have to sweat the details
* Supporting various targets `:browser`, `:node-script`, `:npm-module`, `:react-native` (experimental)...
* Live Reload (CLJS + CSS)
* CLJS REPL
* Importing CommonJS & ES6 modules from npm or local JavaScript files
* Code splitting (via :`modules`)
* Fast builds, reliable caching, ...

To use shadow-cljs, add configs in the file `shadow-cljs.edn`, and run:

```bash
npm install -g shadow-cljs # Node.js and JVM are required to be installed

shadow-cljs compile app # `:app` is the build-id defined in shadow-cljs.edn
```

[Try with an demo project!](https://github.com/minimal-xyz/minimal-shadow-cljs-browser)

![shadow-cljs demo](/entry/shadow-cljs-demo.png)

### Configurations

For a browser app, write in `shadow-cljs.edn` like:

```clojure
{:source-paths ["src"]
 :dependencies [[reagent "0.8.1"]]
 :builds {:app {:target :browser
                :output-dir "public/js"
                :asset-path "/js"
                :modules {:main {:init-fn app.main/main!}}}}}
```

which means:

```clojure
{:source-paths ["src"] ; where you put source files

 :dependencies [[reagent "0.8.1"]] ; ClojureScript dependencies

        ; "app" is the build-id, in running "shadow-cljs compile app"
 :builds {:app {:target :browser ; compile code that loads in a browser
                :output-dir "public/js"
                :asset-path "/js" ; assets loaded from index.html are based on path "/js"

                        ; "main.js" is the name for the bundle entry
                :modules {:main {:init-fn app.main/main!}}}}}
                               ; function app.main.main! is called when page loads
```

You may write `:target :node-script` [for running in Node.js](https://github.com/minimal-xyz/minimal-shadow-cljs-nodejs) and also try [more targets](https://shadow-cljs.github.io/docs/UsersGuide.html#_build_target).

### CLI tools

Some useful shadow-cljs commands during development are:

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

Find out more [commands in the docs](https://shadow-cljs.github.io/docs/UsersGuide.html#_command_line).

### Using modules from npm

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
 :dev-http {8080 "target/"}
 :builds {:browser {:target :browser
                    :output-dir "target/browser"
                    :modules {:main {:init-fn app.main/main!}}

                    :devtools {:after-load app.main/reload!}}}}
```

### Long term caching

By setting in `:module-hash-names` field, you may tell shadow-cljs to add MD5 hash in the filenames generated. It's a trivial feature in Webpack, now it's one-liner config in ClojureScript. Meanwhile `assets.edn` file can be emitted for indexing js files in HTML.

```clojure
{:source-paths ["src"]
 :dependencies [[mvc-works/hsl "0.1.2"]]
 :builds {:browser {:target :browser
                    :output-dir "target/browser"
                    :modules {:main {:init-fn app.main/main!}}

                    :release {:output-dir "dist/"
                              :module-hash-names 8
                              :build-options {:manifest-name "assets.edn"}}}}}
```

After compilation, two files will be generated in `dist/` with names:

```
=>> l dist/
assets.edn        main.9683CD2F.js
```

### Other features

There are more features in shadow-cljs, such as:

* [Dynamic code loading](https://shadow-cljs.github.io/docs/UsersGuide.html#_loading_code_dynamically)
* [Testing](https://shadow-cljs.github.io/docs/UsersGuide.html#_testing)
* [Editor Integration](https://shadow-cljs.github.io/docs/UsersGuide.html#_editor_integration)

Read [User Guide](https://shadow-cljs.github.io/docs/UsersGuide.html) for more features.

### Getting started

Here are some configurations you can start with:

* [compile a browser app](https://github.com/minimal-xyz/minimal-shadow-cljs-browser/blob/master/shadow-cljs.edn#L6)
* [compile a Node.js script](https://github.com/minimal-xyz/minimal-shadow-cljs-nodejs/blob/master/shadow-cljs.edn#L4)
* [compile with optimizations](https://github.com/minimal-xyz/minimal-shadow-cljs-release/blob/master/package.json#L12)
* [emits multiple bundles and load on need](https://github.com/minimal-xyz/minimal-shadow-cljs-loader/blob/master/shadow-cljs.edn#L8-L10)
* [emits code in CommonJS syntax](https://github.com/minimal-xyz/minimal-shadow-cljs-commonjs/blob/master/shadow-cljs.edn#L3)
