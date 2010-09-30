# clj-rm-rf

Recursively remove directory/file for Clojure.

## Usage

    (use 'hozumi.rm-rf)

    (rm-r (File. "garbage"))
    (rm-r (File. "garbage") :silently)

## Installation

Leiningen
    [clj-rm-rf "1.0.0-SNAPSHOT"]

