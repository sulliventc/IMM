//
// Created by Colten Sullivent on 10/5/17.
//

#include "../include/SmapiControl.h"

void SmapiControl::checkSmapiInstall() {

}

void SmapiControl::checkSmapiVersion() {
// tag_name
}

boost::filesystem::path SmapiControl::downloadSmapi() {
  SerializedTypes::SmapiReleaseResponse::obj response = getReleaseResponse();
  std::string url = "https://github.com/Pathoschild/SMAPI/releases/download/";
  url += response.tag_name;
  url += "/SMAPI-";
  url += response.tag_name;
  url += ".zip";

  return sendGetAndSave(url, "smapi.zip");
}

void SmapiControl::installSmapi() {
  boost::filesystem::path zipPath = SmapiControl::downloadSmapi();

  zipper::Unzipper unzipper(zipPath.string());
  unzipper.extract(boost::filesystem::temp_directory_path().string() + "/smapi");
  unzipper.close();
}

SerializedTypes::SmapiReleaseResponse::obj SmapiControl::getReleaseResponse() {
  const std::string url = "https://api.github.com/repos/Pathoschild/SMAPI/releases/latest";
  const std::string filename = "immSmapiReponse.json";

  boost::filesystem::path path = sendGetAndSave(url, filename);
  SerializedTypes::SmapiReleaseResponse::obj response;
  boost::filesystem::ifstream i(path);
  json j;
  i >> j;
  i.close();
  response = j;

  return response;
}

boost::filesystem::path SmapiControl::sendGetAndSave(std::string url, std::string filename) {
  CURL *curl;
  CURLcode res;
  FILE *fp;
  std::string fullpath = boost::filesystem::temp_directory_path().string() + filename;

  curl = curl_easy_init();
  if (curl) {
    fp = fopen(fullpath.c_str(), "wb");
    curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
    curl_easy_setopt(curl, CURLOPT_USERAGENT, "Iridium Mod Manager");
    curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1);
    curl_easy_setopt(curl, CURLOPT_WRITEDATA, fp);
    res = curl_easy_perform(curl);
    if (res != CURLE_OK) {
      // TODO: Proper error handling
      fprintf(stderr, "curl_easy_perform() failed: %s\n", curl_easy_strerror(res));
    }
    curl_easy_cleanup(curl);
    fclose(fp);
  }

  return boost::filesystem::path(fullpath);
}
