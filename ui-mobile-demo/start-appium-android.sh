#!/bin/bash

function die {
    echo $1
    exit 1
}

pkg_root_dir=`find $PWD | grep "/Config$" | head -n 1 | xargs dirname`
app_filename="$pkg_root_dir/Users/tharindu/cellcard.apk"
ls -1 $app_filename || die "Did not find app in $pkg_root_dir"

appium --pre-launch --app-pkg com.amatak.mycellcard.qa  --app-activity com.amatak.mycellcard.MainActivity --platform-name Android --app $app_filename
