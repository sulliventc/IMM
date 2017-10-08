//
// Created by Colten Sullivent on 10/5/17.
//

#ifndef IMM_SMAPICONTROL_H
#define IMM_SMAPICONTROL_H

#include <boost/filesystem.hpp>
#include <iostream>
#include <curl/curl.h>
#include <json.hpp>
#include <zipper.h>
#include <unzipper.h>
#include <tools.h>
#include "SerializedTypes.h"

using json = nlohmann::json;

class SmapiControl {
private:
  static void checkSmapiInstall();
  static void checkSmapiVersion();
  static boost::filesystem::path downloadSmapi();
  static SerializedTypes::SmapiReleaseResponse::obj getReleaseResponse();
  static boost::filesystem::path sendGetAndSave(std::string, std::string);
public:
  static void installSmapi();
};


#endif //IMM_SMAPICONTROL_H
