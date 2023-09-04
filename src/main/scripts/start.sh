#!/usr/bin/env sh
#
# Copyright (c) 2023. CryingPun4
#
#    Licensed under the Apache License, Version 2.0 (the "License");
#    you may not use this file except in compliance with the License.
#    You may obtain a copy of the License at
#
#        http://www.apache.org/licenses/LICENSE-2.0
#
#    Unless required by applicable law or agreed to in writing, software
#    distributed under the License is distributed on an "AS IS" BASIS,
#    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#    See the License for the specific language governing permissions and
#    limitations under the License.
#
cd "$(dirname "$0")" || exit

# shellcheck disable=SC2154
java -jar "${project.build.finalName}".jar
echo "Press enter to continue . . . "
# shellcheck disable=SC2034
read -r test
